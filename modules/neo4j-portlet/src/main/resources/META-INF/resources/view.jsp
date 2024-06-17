<%@ include file="/init.jsp" %>

<%
 String result = ParamUtil.getString(request, "neo4jQueryResult");
%>

<portlet:actionURL name="/neo4j-query-action" var="queryURL"/>


<div class="container-fluid-1280 edit-assignment">
       <aui:form action="<%= queryURL %>" method="post" name="fm1">
            <aui:fieldset>
                <aui:input
                    label="Cypher"
                    name="cypher"
                    placeholder="$"
                    type="textBox"
                    required = "true"
                />
            </aui:fieldset>
            <aui:fieldset>
                <aui:input
                    cssClass="lfr-textarea-container"
                    disabled="true"
                    label="result"
                    name="result"
                    resizable="true"
                    type="textarea"
                    value="<%= result %>"
                    wrap="soft"
                />
            </aui:fieldset>

            <aui:button-row>
                <aui:button cssClass="btn-lg" type="submit" value="run" />
            </aui:button-row>
       </aui:form>
    </div>
