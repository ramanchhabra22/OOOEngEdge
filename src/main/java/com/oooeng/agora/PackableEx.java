package com.oooeng.agora;

public interface PackableEx extends Packable{
    void unmarshal(ByteBuf in);
}
