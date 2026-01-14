package com.tugalsan.api.gui.gl.client.three4g.resource;

import com.google.gwt.core.client.*;
import com.google.gwt.resources.client.*;
import com.tugalsan.api.list.client.*;
import java.util.*;
import com.tugalsan.api.log.client.*;
  
public interface TGC_GLResourceUtils extends ClientBundle {//VARS CANNOT BE PRIVATE!!!

    final static TGC_Log d = TGC_Log.of(TGC_GLResourceUtils.class);
  
    final public static TGC_GLResourceUtils INSTANCE = GWT.create(TGC_GLResourceUtils.class);

    public static void addLib(TextResource libTextResource) {//EXAMPLE libTextResource: TGC_GLResourceUtils.INSTANCE.getOrbitControls()
        if (isLibAdded(libTextResource.getName())) {
            return;
        }
        ScriptInjector.fromString(libTextResource.getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
        addedLibNames.add(libTextResource.getName());
        d.ci("addLib", "name", libTextResource.getName());
    }
    final public static List<String> addedLibNames = TGS_ListUtils.of();

    public static boolean isLibAdded(String libName) {//example libName: getStatsMin
        return addedLibNames.stream().filter(nm -> Objects.equals(nm, libName)).findAny().isPresent();
    }

    @Source("../../../../../../../../org.treblereel.gwt.three4g.extensions.resources.js/loaders/DRACOLoader.js")
    TextResource getDRACOLoader();

    @Source("../../../../../../../../org.treblereel.gwt.three4g.extensions.resources.js/loaders/GLTFLoader.js")
    TextResource getGLTFLoader();

    @Source("../../../../../../../../org.treblereel.gwt.three4g.extensions.resources.js/controls/OrbitControls.js")
    TextResource getOrbitControls();

    @Source("../../../../../../../../org.treblereel.gwt.three4g.extensions.resources.js/controls/TrackballControls.js")
    TextResource getTrackballControls();

    @Source("../../../../../../../../org.treblereel.gwt.three4g.extensions.resources.js/utils/SkeletonUtils.js")
    TextResource getSkeletonUtils();

    @Source("stats.min.js")
    TextResource getStatsMin();

    @Source("ParametricGeometries.js")
    TextResource getParametricGeometries();

    @Source("TeapotBufferGeometry.js")
    TextResource getTeapotBufferGeometry();

//    @Source("controls/FirstPersonControls.js")
//    TextResource getFirstPersonControls();
//
//    @Source("controls/TrackballControls.js")
//    TextResource getTrackballControls();
//
//    @Source("controls/FlyControls.js")
//    TextResource getFlyControls();
//
//    @Source("CurveExtras.js")
//    TextResource getCurveExtras();
//
//    @Source("GPUComputationRenderer.js")
//    TextResource getGPUComputationRenderer();
//
//    @Source("birdGeometry.js")
//    TextResource getBirdGeometry();
//
//    @Source("RollerCoaster.js")
//    TextResource getRollercoaster();
//
//    @Source("PaintViveController.js")
//    TextResource getPaintViveController();
//
//    @Source("MarchingCubes.js")
//    TextResource getMarchingCubes();
//
//    @Source("Tween.js")
//    TextResource getTWEEN();
//
//    @Source("ShaderSkin.js")
//    TextResource getShaderSkin();
//
//    @Source("CopyShader.js")
//    TextResource getCopyShader();
//
//    @Source("EffectComposer.js")
//    TextResource getEffectComposer();
//
//    @Source("RenderPass.js")
//    TextResource getRenderPass();
//
//    @Source("ShaderPass.js")
//    TextResource getShaderPass();
//
//    @Source("MaskPass.js")
//    TextResource getMaskPass();
//
//    @Source("ImprovedNoise.js")
//    TextResource getImprovedNoise();
}
