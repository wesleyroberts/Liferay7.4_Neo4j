package br.com.neo4j.portlet.command.action;

import br.com.neo4j.Converter.CustomConverter;
import br.com.neo4j.configuration.Neo4jGraphDatabaseConfiguration;
import br.com.neo4j.connector.Neo4jConnector;
import br.com.neo4j.constants.Neo4jPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;


import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component(
        configurationPid = "br.com.neo4j.configuration.Neo4jGraphDatabaseConfiguration",
        configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
        property = {
                "javax.portlet.name=" + Neo4jPortletKeys.NEO4J,
                "mvc.command.name=" + "/neo4j-query-action"
        },
        service = MVCActionCommand.class
)
public class Neo4jQueryActionMVCCommand extends BaseMVCActionCommand {
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String cypher = ParamUtil.getString(actionRequest, "cypher");
        String hostname = configuration.get().hostname() + configuration.get().port();
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        Session session = Neo4jConnector.connect(
                hostname,
                configuration.get().userName(),
                configuration.get().password()
        );

        if (session == null) {
            SessionErrors.add(actionRequest, Exception.class);
            actionResponse.getRenderParameters(
            ).setValue(
                    "mvcRenderCommandName", "/b3-MFA-render"
            );
            sendRedirect(actionRequest, actionResponse);
        }

        StatementResult result = session.run(cypher);

        result
                .list(r -> r.asMap(CustomConverter::convert))
                .stream()
                .map(Neo4jQueryActionMVCCommand::toJson)
                .forEach(jsonArray::put);

        session.close();

        actionResponse.setRenderParameter(
                "neo4jQueryResult", jsonArray.toString()
        );
    }
    protected static JSONObject toJson(Map<String, Object> map) {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jsonObject.put(entry.getKey(), entry.getValue());
        }

        return jsonObject;
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        Neo4jGraphDatabaseConfiguration newConfig = ConfigurableUtil.createConfigurable(
                Neo4jGraphDatabaseConfiguration.class, properties);
        configuration.set(newConfig);
    }


    private final AtomicReference<Neo4jGraphDatabaseConfiguration> configuration = new AtomicReference<>();

}
