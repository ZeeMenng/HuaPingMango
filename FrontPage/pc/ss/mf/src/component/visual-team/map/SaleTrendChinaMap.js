import React from 'react';
import ReactThreeBase from '../base/ReactThreeBase';
import * as THREE from 'three';
import {GeneralMapApp, ColorScale, ChartLib} from '../../../vendor/threejs-chart-library-apps.min.js';

import diamond from './diamond.png';

//import * as ChartLib from './threejs-chart-library-apps.min.js';
// FOR TEST
// window.devicePixelRatio = 0.5;

/**
 *
 */
class ChinaMap extends ReactThreeBase {
  constructor(props) {
    super(props);
    let me = this;

    let env = new GeneralMapApp({
      cameraPosition: [10.808370057456004, -15.364198757897254, 341.96305373876106],
      scaleX: 11,
      scaleY: 13,
      fillColor: ['#21a5f0', '#1db0f2', '#1bb4f2'],  //['#5efdff', '#47fdff', 0x38fcff],
      lightColor: 0xCCCCCC,
      labelStrokeColor: 0xFFFFFF,
      labelScale:1.5,
      labelFillColor: 0xFFFFFF,
      southChinaSea: {
        position: [250, -180, 0],
        fillColor: '#21a5f0'
      },
      useRank: false
    });
    env._initLights = function () {
      let me2 = this;
      let c = 0.25;
      let c2 = 0xFFFFFF;
      me2.addPointLight(c2, 0.2, 1200.0 * c, 0.0, 100.0 * c);
      me2.addPointLight(c2, 0.2, -1200.0 * c, 0.0, 100.0 * c);
      me2.addPointLight(c2, 0.2, 0.0, 1200.0 * c, 100.0 * c);
      me2.addPointLight(c2, 0.2, 0.0, -1200.0 * c, 100.0 * c);
      me2.addPointLight(c2, 1.1, 0.0, 0.0, 1200.0 * c);

      me2.controls.enableZoom = false;
    };
    // env._initFinally = function () {
    //   let me2 = this;
    //   me2.camera.position.set();
    //   me2.controls.update();
    //   me2.controls.enableZoom = false;
    // };
    me._env = env;
  }

  set lineData(value) {
    // 柱子的颜色比例尺，这里假设数据的值域domain为[0,1]，实际应从数据中抽取出值域，下同
    let color = new ColorScale()
      .domain(0, 1)
      .colors(0xF2ED3B, 0xECD137, 0xE08F2D, 0xD72D30);
    //console.log(value);
        //流向线的颜色
        value.forEach(v => {
            if (v.mount > 0.5) {
              v.color = '#EDE527';
            } else {
              v.color = "#C6ED27";
            }
          });
    /* value.forEach(v => {
      if (v.mount > 0.5) {
        v.color = 0xFDE634;
      } else {
        v.color = 0x00FFFF;
      }
      // v.color2 = 0xff8ba6;
      // v.color3 = 0xb3267f;
    }); */
    this._env.lineData = value;
  }

  set markerData(value) {
    const env = this._env;
    // 柱子的颜色比例尺，这里假设数据的值域domain为[0,1]，实际应从数据中抽取出值域，下同
    let color = new ColorScale()
      .domain(0, 1)
      .colors(0xF2ED3B, 0xECD137, 0xE08F2D, 0xD72D30);
    //const dio = new THREE.Mesh({ map: THREE.ImageUtils.loadTexture('http://wow.techbrood.com/uploads/1702/crate.jpg') });
    // const dio = new THREE.Mesh({ map: THREE.ImageUtils.loadTexture(diamond) });

    /* const target = {
      // markerTextures:dio,
      lonLat: [100.25, 26.86],
      mount: 0.8333333333333334,
      size: 3,
      color: '#fff',

    }; */
    //发出点颜色
    const target = {
        // markerTextures:dio,
        lonLat: [100.25, 26.86],
        mount: 0.8333333333333334,
        size: 3,
        color: '#AE81FF',

    };

    const position = new THREE.Vector2(...target.lonLat);
    const xy = env._generalMap.map.lonLatToXY(position.x, position.y);
    const geometry = new THREE.BoxBufferGeometry(30, 31, 30);
    const material = new THREE.MeshBasicMaterial({map: THREE.ImageUtils.loadTexture(diamond)});
    const dio = new THREE.Mesh(geometry, material);
    dio.position.x = xy.x + 15;
    dio.position.y = xy.y + 31;
    dio.position.z = 30;
    dio.rotation.x = 45;
    //env.addObject(dio);
    /* value.forEach(v => {
      //console.log(v);
      if (v.mount > 0.5) {
        v.color = 0xFDE634;
      } else {
        v.color = 0X00FFFF;
      }
      // v.color2 = 0xff8ba6;
      // v.color3 = 0xb3267f;
    }); */
    value.forEach(v => {
        //console.log(v);
        //流入点颜色
        if (v.mount > 0.5) {
          v.color = '#E6DB74';
        } else {
          v.color = "#FFFF89";
        }
        // v.color2 = 0xff8ba6;
        // v.color3 = 0xb3267f;
      });
    value.push(target);
    //console.log(value);
    this._env.markerData = value;
  }
}

export default ChinaMap;
