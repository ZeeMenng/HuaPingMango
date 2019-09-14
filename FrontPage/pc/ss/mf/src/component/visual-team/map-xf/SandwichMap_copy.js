// import { BoundingBox, createElement, setStyles, UiComponent } from '../vendor/common-library.min.js';
import { BoundingBox, createElement, setStyles, UiComponent } from '../../../vendor/common-library.min.js';

import {
  getGlobalPosition, getLocalPosition
} from '@jusfoun-vis/scaler';

/**
 * 将数值型RGB颜色转换为十六进制Hex形式。
 * @author Molay
 * @param {number} rgb
 * @return {string}
 */
const rgbToHex = function (rgb) {
  let hex = rgb.toString(16);
  for (let i = 0, n = 6 - hex.length; i < n; i++) {
    hex = '0' + hex
  }
  return '#' + hex;
};

/**
 * 基于标准GeoJSON数据，采用Canvas2D绘制技术，实现夹层板块样式。
 * 其使用了基本技巧来实现不同部分的绘制以及交互，此例供大家学习和了解，后续会更改为统一的OOP模式。
 * 注意，此组件使用原生JS实现，可封装用于任何前端框架中。
 * @author Molay
 */
class SandwichMap extends UiComponent {
  constructor(option) {
    super();

    let me = this;

    option = option || {};
    me._option = option;

    let domElement = createElement('div');
    me._domElement = domElement;
    setStyles(domElement, {
      position: 'relative'
    });

    let canvasSandwichLevel = createElement('canvas');
    me._canvasSandwichLevel = canvasSandwichLevel;
    setStyles(canvasSandwichLevel, {
      position: 'absolute',
      left: '0',
      top: '0'
    });
    domElement.appendChild(canvasSandwichLevel);

    let canvasTopLevel = createElement('canvas');
    me._canvasTopLevel = canvasTopLevel;
    setStyles(canvasTopLevel, {
      position: 'absolute',
      left: '0',
      top: '0'
    });
    domElement.appendChild(canvasTopLevel);

    // 鼠标检测
    domElement.addEventListener('mousemove', function (event) {
      let geoJson = me._geoJson;
      if (!geoJson) return me._setIntersection();

      let p = getLocalPosition(event, domElement);
      let mouseX = p.x;
      let mouseY = p.y;
      // console.log(domElement.getBoundingClientRect());

      let context2d = hitArea.getContext('2d');
      let rgba = context2d.getImageData(mouseX, mouseY, 1, 1).data;

      if (rgba[3] !== 0xFF) {
        me.fire({
          type: 'mouseout',
        });
        return me._setIntersection();
      }
      let r = rgba[0], g = rgba[1], b = rgba[2];
      let index = (r << 16) + (g << 8) + b;
      if (index < 1) return me._setIntersection();

      index--;
      // console.log(geoJson.features);
      let feature = geoJson.features[index];
      // console.log(index)
      if (feature) {
        me._setIntersection(feature);
        // console.log(feature);
        let cp = feature.properties.cp;
        me.fire({
          type: "hover",
          event,
          position: me.lonLatToLocal(...cp),
          feature
        });
      }
      // else console.warn('Hit area may have some problems.');
    });

    domElement.addEventListener('click', function (e) {
      let geoJson = me._geoJson;
      if (!geoJson) return false;

      let p = getLocalPosition(e, domElement);
      let mouseX = p.x;
      let mouseY = p.y;

      let context2d = hitArea.getContext('2d');
      let rgba = context2d.getImageData(mouseX, mouseY, 1, 1).data;
      if (rgba[3] !== 0xFF) return me.false;
      let r = rgba[0], g = rgba[1], b = rgba[2];
      let index = (r << 16) + (g << 8) + b;
      if (index < 1) return me.false;

      index--;
      let feature = geoJson.features[index];
      if (feature) {
        let cp = feature.properties.cp;
        option.clickCallBack(Object.assign({
          event: e,
          position: me.lonLatToLocal(...cp)
        }, { feature }));
      }
      // else console.warn('Hit area may have some problems.');
    });

    let hitArea = createElement('canvas');
    me._hitArea = hitArea;

    let drawParams = {
      scaleX: 1.0,
      scaleY: 1.0,
      offsetX: 0.0,
      offsetY: 0.0
    };
    me._drawParams = drawParams;

    me._cacheSandwichLevel = new Image();
    me._cacheTopLevel = new Image();

    me._cacheTopLevels = {};
  }

  _option = undefined;

  _canvasSandwichLevel = undefined;
  _canvasTopLevel = undefined;
  _hitArea = undefined;

  _boundingBox = undefined;
  _drawParams = undefined;
  _dataMap = undefined;
  _intersection = undefined;

  /**
   * 指示视口尺寸发生变更，比如width、height被设置，全部内容将进行重新绘制。
   * @type {boolean}
   * @private
   */
  _invalidateViewportSizeFlag = false;
  /**
   * 指示整体渲染发生变更，如更改GeoJSON等，全部内容将进行重新绘制。
   * @type {boolean}
   * @private
   */
  _invalidateRenderFlag = false;
  /**
   * 指示顶层渲染发生变更，如更改data等，顶层将进行重新绘制。
   * @type {boolean}
   * @private
   */
  _invalidateTopLevelFlag = false;
  /**
   * 指示夹层渲染发生变更，夹层将进行重新绘制。
   * @type {boolean}
   * @private
   */
  _invalidateSandwichLevelFlag = false;

  _domElement = undefined;
  get domElement() {
    return this._domElement;
  }

  _geoJson = undefined;
  /**
   * 此地图的GeoJSON信息。
   */
  get geoJson() {
    return this._geoJson;
  }

  set geoJson(value) {
    let me = this;
    me._geoJson = value;
    me._updateBoundingBox();
    me._invalidateRender();
  }

  _numSandwiches = 1;
  /**
   * 具备的夹层数，最少为1层，其中顶层固定1层，底层为numSandwiches-1。
   */
  get numSandwiches() {
    return this._numSandwiches;
  }

  set numSandwiches(value) {
    value = Math.floor(value);
    if (!isFinite(value) || value < 1) throw new Error('The number of sandwiches must greater than 1.');

    let me = this;
    me._numSandwiches = value;
    me._invalidateRender();
  }

  _sandwichGap = 1;
  /**
   * 夹层间隔。
   */
  get sandwichGap() {
    return this._sandwichGap;
  }

  set sandwichGap(value) {
    let me = this;
    me._sandwichGap = value;
    me._invalidateRender();
  }

  _scaleX = 1.0;
  /**
   * 宽度缩放。
   * 当自动缩放关闭时，将使用此值；当自动缩放开启时，将忽略此值。
   */
  get scaleX() {
    return this._scaleX;
  }

  set scaleX(value) {
    let me = this;
    me._scaleX = value;
    me._invalidateRender();
  }

  _scaleY = 1.0;
  /**
   * 高度缩放。
   * 当自动缩放关闭时，将使用此值；当自动缩放开启时，将忽略此值。
   */
  get scaleY() {
    return this._scaleY;
  }

  set scaleY(value) {
    let me = this;
    me._scaleY = value;
    me._invalidateRender();
  }

  _data = undefined;
  /**
   * 板块的数据，可为每个板块赋值。
   * 数据格式为：
   * [
   *   {
   *     id: '板块Id',
   *     ... //其他信息
   *   }
   * ]
   */
  get data() {
    return this._data;
  }

  set data(value) {
    let me = this;
    me._data = value;
    let dataMap = {};
    value.forEach(v => {
      dataMap[v.id] = v;
    });
    me._dataMap = dataMap;
    me._invalidateTopLevel();
  }

  _idField = undefined;
  /**
   * 板块Id字段，指示读取feature中何字段来取得板块的Id。
   */
  get idField() {
    return this._idField;
  }

  set idField(value) {
    let me = this;
    me._idField = value;
    me._invalidateTopLevel();
  }

  _autoScale = false;
  /**
   * 自动缩放，开启时将自动计算最合适的宽高缩放。
   */
  get autoScale() {
    return this._autoScale;
  }

  set autoScale(value) {
    let me = this;
    me._autoScale = value;
    me._invalidateRender();
  }

  _aspectRatio = 1.0;
  /**
   * 宽高比，自动缩放开启时，将使用此值计算宽高的比例。
   */
  get aspectRatio() {
    return this._aspectRatio;
  }

  set aspectRatio(value) {
    let me = this;
    me._aspectRatio = value;
    me._invalidateRender();
  }

  _padding = 0.0;
  /**
   * 上下左右填充空隙，自动缩放开启时，此值将参与宽高比例计算。
   */
  get padding() {
    return this._padding;
  }

  set padding(value) {
    let me = this;
    me._padding = value;
    me._invalidateRender();
  }

  set width(value) {
    super.width = value;
    this._invalidateViewportSize();
  }

  set height(value) {
    super.height = value;
    this._invalidateViewportSize();
  }

  /**
   * 经纬度转换为本地坐标。
   * 注意，此坐标计算时忽视整个场景被缩放的情况。
   * @param lon
   * @param lat
   * @return {*}
   */
  lonLatToLocal(lon, lat) {
    let me = this;
    let drawParams = me._drawParams;
    if (!drawParams) return {
      x: me._width / 2,
      y: me._height / 2
    };
    let scaleX = drawParams.scaleX;
    let scaleY = drawParams.scaleY;
    let offsetX = drawParams.offsetX;
    let offsetY = drawParams.offsetY;

    return {
      x: lon * scaleX + offsetX,
      y: -lat * scaleY + offsetY
    }
  }

  _invalidateViewportSize() {
    let me = this;
    if (me._invalidateViewportSizeFlag) return;
    me._invalidateViewportSizeFlag = true;
    me.invalidateProperties();
  }

  _invalidateRender() {
    let me = this;
    if (me._invalidateRenderFlag) return;
    me._invalidateRenderFlag = true;
    me.invalidateProperties();
  }

  _invalidateTopLevel() {
    let me = this;
    if (me._invalidateTopLevelFlag) return;
    me._invalidateTopLevelFlag = true;
    me.invalidateProperties();
  }

  _invalidateSandwichLevel() {
    let me = this;
    if (me._invalidateSandwichLevelFlag) return;
    me._invalidateSandwichLevelFlag = true;
    me.invalidateProperties();
  }

  _resetCanvas(canvas) {
    let context2d = canvas.getContext('2d');
    context2d.clearRect(0, 0, canvas.width, canvas.height);
    return canvas;
  };

  /**
   * 设置当前焦点板块。
   * @param intersection
   * @private
   */
  _setIntersection(intersection) {
    let me = this;
    if (me._intersection === intersection) return;
    me._intersection = intersection;
    me._invalidateTopLevel();
    setStyles(me._domElement, {
      cursor: intersection ? 'pointer' : ''
    });
    // console.log('intersection changed.');
  }

  /**
   * 更新GeoJSON全局边界盒以及各板块的边界盒，边界为经纬度。
   * @private
   */
  _updateBoundingBox() {
    let me = this;
    let geoJson = me._geoJson;
    let boundingBox = new BoundingBox();
    geoJson.features.forEach((feature, index) => {
      let featureBoundingBox = new BoundingBox();
      feature.boundingBox = featureBoundingBox;
      feature.index = index;

      let geometry = feature.geometry;
      if (geometry.type === 'Polygon') {
        geometry.coordinates[0].forEach(coord => {
          featureBoundingBox.expand(coord[0], coord[1]);
        });
      }
      else if (geometry.type === 'MultiPolygon') {
        geometry.coordinates.forEach(coordinates => {
          coordinates[0].forEach(coord => {
            featureBoundingBox.expand(coord[0], coord[1]);
          });
        });
      }
      else console.warn('Unsupported geometry type:', geometry);

      featureBoundingBox.enrich();
      boundingBox.expand(featureBoundingBox.minX, featureBoundingBox.minY);
      boundingBox.expand(featureBoundingBox.maxX, featureBoundingBox.maxY);
    });

    boundingBox.enrich();
    me._boundingBox = boundingBox;
  }

  /**
   * 更新鼠标事件触发区，用于快速检测鼠标触发。
   * @private
   */
  _updateHitArea() {
    let me = this;

    let hitArea = me._resetCanvas(me._hitArea);
    let context2d = hitArea.getContext('2d');

    let geoJson = me._geoJson;
    geoJson.features.forEach((feature, index) => {
      let color = rgbToHex(index + 1);

      let geometry = feature.geometry;
      if (geometry.type === 'Polygon') {
        context2d.beginPath();
        geometry.coordinates[0].forEach((coord, index) => {
          let p = me.lonLatToLocal(coord[0], coord[1]);
          let x = p.x;
          let y = p.y;
          index === 0 ? context2d.moveTo(x, y) : context2d.lineTo(x, y);
        });
        context2d.closePath();

        context2d.globalAlpha = 1.0;
        context2d.fillStyle = color;
        context2d.strokeStyle = color;
        context2d.fill();
        context2d.stroke();
      }
      else if (geometry.type === 'MultiPolygon') {
        geometry.coordinates.forEach(coordinates => {
          context2d.beginPath();
          coordinates[0].forEach((coord, index) => {
            let p = me.lonLatToLocal(coord[0], coord[1]);
            let x = p.x;
            let y = p.y;
            index === 0 ? context2d.moveTo(x, y) : context2d.lineTo(x, y);
          });
          context2d.closePath();

          context2d.globalAlpha = 1.0;
          context2d.fillStyle = color;
          context2d.strokeStyle = color;
          context2d.fill();
          context2d.stroke();
        });
      }
      else console.warn('Unsupported geometry type:', geometry);
    });
  }

  _updateTopLevel() {
    let me = this;

    let canvas = me._resetCanvas(me._canvasTopLevel);
    let context2d = canvas.getContext('2d');

    let intersection = me._intersection;

    let geoJson = me._geoJson;
    geoJson.features.forEach(feature => {
      if (feature === intersection) return;
      me._updateTopLevelFeature(feature, context2d);
    });

    if (intersection)
      me._updateTopLevelFeature(intersection, context2d);
  }

  _updateTopLevelFeature(feature, context2d) {
    let me = this;

    let idField = me._idField;
    let dataMap = me._dataMap || {};

    let id = feature[idField] || feature.properties[idField];
    let data = dataMap[id];

    let geometry = feature.geometry;
    if (geometry.type === 'Polygon') {
      context2d.beginPath();
      geometry.coordinates[0].forEach((coord, index) => {
        let p = me.lonLatToLocal(coord[0], coord[1]);
        let x = p.x;
        let y = p.y;
        index === 0 ? context2d.moveTo(x, y) : context2d.lineTo(x, y);
      });
      context2d.closePath();
      me._drawTopLevelFeature(context2d, feature, data);
    }
    else if (geometry.type === 'MultiPolygon') {
      geometry.coordinates.forEach(coordinates => {
        context2d.beginPath();
        coordinates[0].forEach((coord, index) => {
          let p = me.lonLatToLocal(coord[0], coord[1]);
          let x = p.x;
          let y = p.y;
          index === 0 ? context2d.moveTo(x, y) : context2d.lineTo(x, y);
        });
        context2d.closePath();
        me._drawTopLevelFeature(context2d, feature, data);
      });
    }
    else console.warn('Unsupported geometry type:', geometry);
  }

  _updateSandwichLevel() {
    let me = this;

    let numSandwiches = me._numSandwiches;
    let sandwichGap = me._sandwichGap;

    let canvas = me._resetCanvas(me._canvasSandwichLevel);
    let context2d = canvas.getContext('2d');

    let idField = me._idField;
    let dataMap = me._dataMap || {};

    // for (let i = 1, n = numSandwiches; i < n; i++) {
    for (let i = numSandwiches - 1, n = numSandwiches; i > 0; i--) {
      let geoJson = me._geoJson;
      geoJson.features.forEach(feature => {
        let id = feature[idField] || feature.properties[idField];
        let data = dataMap[id];

        let geometry = feature.geometry;
        if (geometry.type === 'Polygon') {
          context2d.beginPath();
          geometry.coordinates[0].forEach((coord, index) => {
            let p = me.lonLatToLocal(coord[0], coord[1]);
            let x = p.x;
            let y = p.y + sandwichGap * i;
            index === 0 ? context2d.moveTo(x, y) : context2d.lineTo(x, y);
          });
          context2d.closePath();
          me._drawSandwichLevelFeature(context2d, feature, data, i - 1, n - 1);
        }
        else if (geometry.type === 'MultiPolygon') {
          geometry.coordinates.forEach(coordinates => {
            context2d.beginPath();
            coordinates[0].forEach((coord, index) => {
              let p = me.lonLatToLocal(coord[0], coord[1]);
              let x = p.x;
              let y = p.y + sandwichGap * i;
              index === 0 ? context2d.moveTo(x, y) : context2d.lineTo(x, y);
            });
            context2d.closePath();
            me._drawSandwichLevelFeature(context2d, feature, data, i - 1, n - 1);
          });
        }
        else console.warn('Unsupported geometry type:', geometry);
      });
    }
  }

  _drawTopLevelFeature(context2d, feature, data) {
    const palette = ['#0E52BC', '#1370D9', '#1B9AD9', '#20A5C9'];
    let emphasis = this._intersection === feature;

    let fillColor = emphasis ? '#FFFF00' : palette[feature.index % palette.length];
    let strokeColor = emphasis ? '#FED231' : '#82F5FC';

    context2d.globalAlpha = 1.0;
    context2d.fillStyle = fillColor;
    context2d.fill();

    context2d.globalAlpha = 1.0;
    context2d.shadowBlur = 30.0;
    context2d.shadowColor = 'rgba(255, 255, 255, 0.5)';
    context2d.strokeStyle = strokeColor;
    context2d.lineWidth = 4.0;
    context2d.stroke();

    context2d.globalAlpha = 1.0;
  }

  _drawSandwichLevelFeature(context2d, feature, data, index, total) {
    let alpha = 1.0 / total * (total - index);
    context2d.globalAlpha = alpha;
    context2d.strokeStyle = '#3FC1DA';
    context2d.lineWidth = 1.0;
    context2d.stroke();

    context2d.globalAlpha = 1.0;
  }

  updateDisplayList() {
  }

  commitProperties() {
    let me = this;

    // 更新视口各绘制层的尺寸
    if (me._invalidateViewportSizeFlag) {
      me._invalidateViewportSizeFlag = false;

      let canvasTopLevel = me._canvasTopLevel;
      canvasTopLevel.width = me._width;
      canvasTopLevel.height = me._height;

      let canvasSandwichLevel = me._canvasSandwichLevel;
      canvasSandwichLevel.width = canvasTopLevel.width;
      canvasSandwichLevel.height = canvasTopLevel.height;

      let hitArea = me._hitArea;
      hitArea.width = canvasTopLevel.width;
      hitArea.height = canvasTopLevel.height;

      me._invalidateRenderFlag = true;
    }

    if (me._invalidateRenderFlag) {
      me._invalidateRenderFlag = false;

      let width = me._width;
      let height = me._height;
      let boundingBox = me._boundingBox;

      let numSandwiches = me._numSandwiches;
      let sandwichGap = me._sandwichGap;
      let sandwichesHeight = (numSandwiches - 1) * sandwichGap;

      let scaleX = me._scaleX;
      let scaleY = me._scaleY;
      let offsetX = width / 2;
      let offsetY = height / 2 - sandwichesHeight / 2;

      // 自动缩放开启
      if (me._autoScale) {
        let padding = me._padding;
        let spaceWidth = width - padding * 2;
        let spaceHeight = height - padding * 2 - sandwichesHeight;
        scaleX = spaceWidth / boundingBox.width;
        scaleY = spaceHeight / boundingBox.height;
        // 锁定宽高比
        let aspectRatio = me._aspectRatio;
        if (scaleX < scaleY) {
          scaleY = scaleX / aspectRatio;
        }
        else scaleX = scaleY * aspectRatio;
      }

      offsetX += -boundingBox.centerX * scaleX;
      offsetY += boundingBox.centerY * scaleY;

      // 存储绘制参数
      let drawParams = me._drawParams;
      drawParams.scaleX = scaleX;
      drawParams.scaleY = scaleY;
      drawParams.offsetX = offsetX;
      drawParams.offsetY = offsetY;

      me._updateHitArea();

      me._invalidateSandwichLevelFlag = true;
      me._invalidateTopLevelFlag = true;
    }

    if (me._invalidateSandwichLevelFlag) {
      me._invalidateSandwichLevelFlag = false;
      me._updateSandwichLevel();
    }

    if (me._invalidateTopLevelFlag) {
      me._invalidateTopLevelFlag = false;
      me._updateTopLevel();
    }
  }

}

export default SandwichMap;
