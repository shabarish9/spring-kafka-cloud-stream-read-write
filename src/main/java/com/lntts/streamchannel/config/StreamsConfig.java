package com.lntts.streamchannel.config;

import com.lntts.streamchannel.stream.StreamsChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(StreamsChannel.class)
public class StreamsConfig {
}
