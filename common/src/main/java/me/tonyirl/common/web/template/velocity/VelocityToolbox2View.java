package me.tonyirl.common.web.template.velocity;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by tony on 16-4-17.
 */
public class VelocityToolbox2View extends VelocityToolboxView {

    @Override
    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViewToolContext ctx = new ViewToolContext(this.getVelocityEngine(), request, response, this.getServletContext());
        if (null != this.getToolboxConfigLocation()) {
            ToolManager tm = new ToolManager();
            tm.setVelocityEngine(this.getVelocityEngine());
            tm.configure(this.getServletContext().getRealPath(this.getToolboxConfigLocation()));
            for (String scope : Scope.values()) {
                ctx.addToolbox(tm.getToolboxFactory().createToolbox(scope));
            }
        }
        if (null != model && !model.isEmpty()) {
            ctx.putAll(model);
        }
        return ctx;
    }
}
