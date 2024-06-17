package br.com.neo4j.configuration;


import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author wesleyrberts
 */

@ExtendedObjectClassDefinition(
        category = "Neo4j",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "br.com.neo4j.configuration.Neo4jGraphDatabaseConfiguration",
        name = "Liferay Neo4j Service Configuration"
)
public interface Neo4jGraphDatabaseConfiguration {
    @Meta.AD(
            name = "UserName defined to neo4j.",
            deflt = "neo4j",
            required = true
    )
    public String userName();

    @Meta.AD(
            name = "Password defined to neo4j.",
            deflt = "neo4j",
            required = true
    )
    public String password();

    @Meta.AD(
            name = "host defined to neo4j.",
            deflt = "localhost",
            required = true
    )
    public String hostname();

    @Meta.AD(
            name = "Bolt port defined to neo4j.",
            deflt = "7687",
            required = true
    )
    public long port();

    @Meta.AD(
            name = "Pool size default to our application.",
            deflt = "50",
            required = false
    )
    public int connectionPoolSize();

    @Meta.AD(
            deflt = "data/neo4j/default"
    )
    public String embeddedDatabasePath();
}
