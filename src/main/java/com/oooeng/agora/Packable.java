package com.oooeng.agora;

public interface Packable {
    ByteBuf marshal(ByteBuf out);
}
