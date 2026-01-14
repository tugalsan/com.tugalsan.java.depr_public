package com.tugalsan.api.gui.gl.client.three4g.common;

import com.tugalsan.api.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.api.gui.gl.client.three4g.resource.*;
import com.tugalsan.api.shape.client.TGS_ShapeDimension;
import com.tugalsan.api.shape.client.TGS_ShapeLocation;
import com.tugalsan.api.shape.client.TGS_ShapeRectangle;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

public class TGC_GLStats {

    public TGC_GLStats(TGC_GLProgramAbstract program, TGS_ShapeLocation location, boolean visible) {
        this.program = program;
        TGC_GLResourceUtils.addLib(TGC_GLResourceUtils.INSTANCE.getStatsMin());
        stats = new TGC_3JSStatsNative();
        stats.dom.style.position = "inherit";
        container = (HTMLDivElement) DomGlobal.document.createElement("div");
        container.style.bottom = "0";
        container.style.right = "0";
        container.appendChild(stats.dom);
        positionAndLocation = new TGS_ShapeRectangle(location, dimension);
        TGC_GLDomUtils.setLocationAndDimension(container, positionAndLocation);
        TGC_GLDomUtils.addToBody(container);
        setVisible(visible);
    }
    final public static TGS_ShapeDimension dimension = new TGS_ShapeDimension(80, 48);
    final private TGC_GLProgramAbstract program;
    final public TGC_3JSStatsNative stats;
    public HTMLDivElement container;
    public TGS_ShapeRectangle positionAndLocation;

    public void rePaint() {
        stats.update();
    }

    final public void setVisible(boolean visible) {
        TGC_GLDomUtils.setVisible(stats.dom, visible);
    }

    /**
     * @author Dmitrii Tikhomirov <chani@me.com>
     * Created by treblereel on 7/12/18.
     */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Stats")
    private static class TGC_3JSStatsNative {

        public HTMLCanvasElement dom;

        public native void update();
    }
}
