package me.nasonov.app;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.elasticsearch.common.transport.TransportAddress;
import java.net.InetAddress;
import com.google.common.net.InetAddresses;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import java.util.List;
import java.util.Arrays;
//import com.floragunn.searchguard.SearchGuardSSLPlugin;
import com.floragunn.searchguard.ssl.SearchGuardSSLPlugin;
import com.floragunn.searchguard.ssl.util.SSLConfigConstants;
//import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		//IPAddressString str = new IPAddressString("10.17.68.218");
		//IPAddress addr = str.toAddress();//throws if invalid, without a DNS lookup
		//InetAddress inetAddr = addr.toInetAddress();
        //TransportAddress address = new InetSocketTransportAddress(InetAddresses.forString("10.17.68.218"), 9300);
        //TransportAddress address = new InetSocketTransportAddress(new InetAddress("10.17.68.218"), 9300);

        //Client client = new PreBuiltTransportClient(Settings.EMPTY, SearchGuardPlugin.class)
Settings settings = Settings.builder()
                .put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_PEMCERT_FILEPATH, "/Users/nasonov/Dropbox/riskmatch/esapp/admin.crt")
                .put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_PEMKEY_FILEPATH, "/Users/nasonov/Dropbox/riskmatch/esapp/admin.key")
                .put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_PEMTRUSTEDCAS_FILEPATH, "/Users/nasonov/Dropbox/riskmatch/esapp/ca.crt")
                .put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_ENFORCE_HOSTNAME_VERIFICATION, false)
                .put("path.home", "/Users/nasonov/Dropbox/riskmatch/esapp")
  .put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_KEYSTORE_PASSWORD, "pass123")
  .put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_TRUSTSTORE_PASSWORD, "pass123")
                .build();
        //Client client = new PreBuiltTransportClient(Settings.EMPTY, SearchGuardSSLPlugin::class.java)
        Client client = new PreBuiltTransportClient(settings, SearchGuardSSLPlugin.class)
            //.addTransportAddress(address);
            .addTransportAddress(new InetSocketTransportAddress(InetAddresses.forString("10.199.11.53"), 9300));
        ImmutableOpenMap<String, IndexMetaData> indices = client.admin().cluster()
                .prepareState().get().getState()
                    .getMetaData().getIndices();
        //for (String key: indices.keySet()) {
        //    System.out.println(key);
        //}
            System.out.println(indices.toString());
    }
}
