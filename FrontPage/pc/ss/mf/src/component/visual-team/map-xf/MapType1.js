// import SandwichMap from './SandwichMap';
import SandwichMap from './SandwichMap_copy';
import { createElement, setStyles } from '../../../vendor/common-library.min.js';
/**
 * 定制地图，主体功能继承自父类（SandwichMap），实现了以下扩展：
 * 1、覆盖继承的_drawTopLevelFeature方法，实现各板块颜色以及点状纹理填充。
 * 2、覆盖继承的_drawSandwichLevelFeature方法，实现自己的夹层效果。
 * 3、提供自定义道路路径绘制。
 * 在这里演示基类提供何种方法，子类如何扩展基类，达到复用基类逻辑，并实现自定义效果的OOP设计目的。
 * @author Molay
 */
class MapType1 extends SandwichMap {
  constructor(option) {
    super(option);

    let me = this;

    me._option = option;

    let domElement = me._domElement;

    let canvasPath = createElement('canvas');
    setStyles(canvasPath, {
      position: 'absolute',
      left: '0',
      top: '0'
    });
    me._canvasPath = canvasPath;
    domElement.appendChild(canvasPath);
  }

  _option = undefined;

  _canvasPath = undefined;

  _fillPattern = undefined;

  /**
   * 指示路径内容发生变更，路径叠层将进行重新绘制。
   * @type {boolean}
   * @private
   */
  _invalidatePathFlag = false;

  _fillTexture = undefined;
  /**
   * 地图顶层填充纹理。
   */
  get fillTexture() {
    return this._fillTexture;
  }

  set fillTexture(value) {
    let me = this;
    me._fillTexture = value;
    // 构建纹理
    if (typeof value === 'string') {
      let image = new Image();
      image.onload = function () {
        let canvas = me._canvasTopLevel;
        let context2d = canvas.getContext('2d');
        me._fillPattern = context2d.createPattern(this, 'repeat');
        me._invalidateTopLevel();
      };
      image.src = value;
    }
    else if (value instanceof Image) {
      //TODO
    }
    me._invalidateTopLevel();
  }

  _fillTextureOpacity = 1.0;
  /**
   * 地图顶层填充纹理的透明度。
   */
  get fillTextureOpacity() {
    return this._fillTextureOpacity;
  }

  set fillTextureOpacity(value) {
    let me = this;
    me._fillTextureOpacity = value;
    me._invalidateTopLevel();
  }

  _pathData = undefined;
  /**
   * 路径数据。
   */
  get pathData() {
    return this._pathData;
  }

  set pathData(value) {
    let me = this;
    me._pathData = value;
    me._invalidatePath();
  }

  _invalidatePath() {
    let me = this;
    if (me._invalidatePathFlag) return;
    me._invalidatePathFlag = true;
    me.invalidateProperties();
  }

  _drawTopLevelFeature(context2d, feature, data) {
    // return super._drawTopLevelFeature(context2d, feature, data);

    let me = this;

    let unShowText = me._option.unShowText;

    let emphasis = me._intersection === feature;

    let color = '#179ffb';
    if (emphasis) color = '#00c6ff';

    // 底色填充
    context2d.globalAlpha = emphasis ? 1.0 : 1.0;
    context2d.fillStyle = color;
    context2d.fill();

    // 纹理填充
    let fillPattern = me._fillPattern;
    let fillTextureOpacity = me._fillTextureOpacity || 1.0;
    if (fillPattern) {
      context2d.globalAlpha = fillTextureOpacity;
      context2d.fillStyle = fillPattern;
      context2d.fill();
    }

    // 笔触
    context2d.save();
    context2d.globalAlpha = 1.0;
    context2d.shadowBlur = emphasis ? 10.0 : 0.0;
    context2d.shadowOffsetY = emphasis ? 5.0 : 0.0;
    // context2d.shadowOffsetX = emphasis ? -2.0 : 0.0;
    context2d.shadowColor = emphasis ? '#46ebff' : 'rgba(0,0,0,0)';
    context2d.strokeStyle = emphasis ? '#46ebff' : 'rgba(13,101,193,.8)';
    context2d.lineWidth = emphasis ? 3.0 : 1.5;
    context2d.stroke();
    context2d.restore();

    // 名称
    // 名称的定位，先找Capital Point，没找到则取几何中心。
    if (!unShowText) {
      let cp = feature.properties.cp;
      if (!cp) cp = [feature.boundingBox.centerX, feature.boundingBox.centerY];
      let p = me.lonLatToLocal(cp[0], cp[1]);
      let fontSize = 24;
      context2d.strokeStyle = '#000';
      context2d.fillStyle = '#ffffff';
      context2d.font = fontSize + 'px 黑体';
      context2d.shadowBlur = 0;
      context2d.shadowColor = 'none';
      let textWidth = context2d.measureText(feature.properties.name).width;
      context2d.strokeText(feature.properties.name, p.x - textWidth / 2, p.y + fontSize / 2);
      context2d.fillText(feature.properties.name, p.x - textWidth / 2, p.y + fontSize / 2);
    }


    context2d.globalAlpha = 1.0;
  }

  _drawSandwichLevelFeature(context2d, feature, data, index, total) {
    // return super._drawSandwichLevelFeature(context2d, feature, data, index, total);

    let alpha = 1.0 / total * (total - index);
    context2d.globalAlpha = alpha;
    context2d.strokeStyle = '#0051b3';
    context2d.lineWidth = 1;
    context2d.stroke();

    context2d.fillStyle = '#0051b3';
    context2d.fill();
    context2d.globalAlpha = 1.0;
  }

  _updatePath() {
    let me = this;

    let canvas = me._resetCanvas(me._canvasPath);
    let context2d = canvas.getContext('2d');

    let pathData = me._pathData;
    if (!pathData) return;

    pathData.forEach(path => {
      let coordinates = path.coordinates;
      let arrowArr = [];
      let arrowArrE = [];
      context2d.beginPath();
      coordinates.forEach((coord, index) => {
        let p = me.lonLatToLocal(coord[0], coord[1]);
        let x = p.x;
        let y = p.y;

        if (index < 2) {
          arrowArr.push([x, y]);
        }

        if (index > coordinates.length - 3) {
          arrowArrE.push([x, y]);
        }

        if (index == 0) {
          context2d.moveTo(x, y);
          context2d.setLineDash([6]);

        } else {
          context2d.lineTo(x, y);
        }
      });

      context2d.save();
      context2d.globalAlpha = .7;
      context2d.lineWidth = 13;
      context2d.strokeStyle = '#36fff0';
      context2d.shadowColor = '#0d65c1';
      context2d.shadowBlur = 4;
      context2d.shadowOffsetY = 10;
      context2d.stroke();

      me._drawArrow(context2d, arrowArr[0][0], arrowArr[0][1], arrowArr[1][0], arrowArr[1][1], true);

      me._drawArrow(context2d, arrowArrE[0][0], arrowArrE[0][1], arrowArrE[1][0], arrowArrE[1][1]);

      context2d.globalAlpha = 1.0;
    });
  }

  _drawArrow(ctx, x1, y1, x2, y2, type) {
    let l = 10;
    let angle = Math.atan((y1 - y2) / (x1 - x2));
    let x = type ? x1 : x2;
    let y = type ? y1 : y2;
    ctx.restore();
    ctx.save();
    ctx.beginPath();

    if (type) {
      ctx.moveTo(x - l * Math.sin(angle), y + l * Math.cos(angle));
      ctx.lineTo(x + l * Math.sin(angle), y - l * Math.cos(angle));
      ctx.lineTo(x - l * Math.cos(angle), y - l * Math.sin(angle));
      ctx.lineWidth = 1;
      ctx.fillStyle = '#36fff0';
      ctx.globalAlpha = .7;
      ctx.shadowColor = '#0d65c1';
      ctx.shadowBlur = 4;
      ctx.shadowOffsetY = 5;
      ctx.fill();
      ctx.restore();
    } else {
      ctx.moveTo(x - l * Math.sin(angle), y + l * Math.cos(angle));
      ctx.lineTo(x + l * Math.sin(angle), y - l * Math.cos(angle));
      ctx.lineTo(x + l * Math.cos(angle), y + l * Math.sin(angle));
      ctx.lineWidth = 1;
      ctx.fillStyle = '#36fff0';
      ctx.globalAlpha = .7;
      ctx.shadowColor = '#0d65c1';
      ctx.shadowBlur = 4;
      ctx.shadowOffsetY = 2;
      ctx.fill();
      ctx.restore();
    }
  }

  commitProperties() {
    let me = this;

    if (me._invalidateViewportSizeFlag) {
      let canvasPath = me._canvasPath;
      canvasPath.width = me._width;
      canvasPath.height = me._height;
    }

    super.commitProperties();

    if (me._invalidatePathFlag) {
      me._invalidatePathFlag = false;

      me._updatePath();
    }
  }
}

export default MapType1;
