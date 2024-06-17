package br.com.neo4j.connector;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.neo4j.driver.v1.*;

/**
 * @author Daniel Kocsis
 */
public class Neo4jConnector {

    private Neo4jConnector() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Session connect(
            String host, String username, String password) {

        if (Validator.isNull(host)) {
            return null;
        }

        if (!host.startsWith(BOLT_PROTOCOL_PREFIX)) {
            host = BOLT_PROTOCOL_PREFIX.concat(host);
        }

        AuthToken authToken = AuthTokens.basic(username,password);

        if (Validator.isNotNull(password)) {
            authToken = AuthTokens.basic(username, password);
        }

        Config config = Config.build()
                .withMaxSessions(50)
                .withEncryptionLevel(Config.EncryptionLevel.NONE)
                .toConfig();

        Session session = null;

        try (Driver driver = GraphDatabase.driver(host, authToken, config)) {
            session = driver.session();

        }
        catch (Exception e) {
            _log.error("Unable to create Neo4j session: " + e);
        }

        return session;
    }

    private static final String BOLT_PROTOCOL_PREFIX = "bolt://";

    private static final Log _log = LogFactoryUtil.getLog(Neo4jConnector.class);

}