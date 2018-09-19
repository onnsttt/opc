package seckil.opc.redis;

public interface KeyPrefix {
    public  int expireSeconds();
    public String getPrefix();
}
