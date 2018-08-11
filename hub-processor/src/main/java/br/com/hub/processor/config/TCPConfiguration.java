package br.com.hub.processor.config;

import br.com.hub.processor.transformers.GsonTransformer;
import br.com.hub.processor.transformers.TransactionTrasformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class TCPConfiguration {

    @Value("${server.port}") Integer port;

    @Bean
    public TcpReceivingChannelAdapter server(TcpNetServerConnectionFactory cf) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(cf);
        adapter.setOutputChannel(inputChannel());
        return adapter;
    }

    @Bean
    public MessageChannel inputChannel() {
        return new QueueChannel();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        return new PollerMetadata();
    }

    @Bean
    public TcpNetServerConnectionFactory cf() {
        return new TcpNetServerConnectionFactory(port);
    }

    @Bean
    public MessageHandler sender() {
        TcpSendingMessageHandler tcpSendingMessageHandler = new TcpSendingMessageHandler();
        tcpSendingMessageHandler.setConnectionFactory(cf());
        return tcpSendingMessageHandler;
    }

    @Bean
    public IntegrationFlow outbound(TransactionTrasformer transactionTrasformer) {
        return IntegrationFlows.from(inputChannel())
                .transform(GsonTransformer.fromJson())
                .transform(transactionTrasformer)
                .transform(GsonTransformer.toJson())
                .handle(sender())
                .get();
    }

}