package bgu.spl.net.impl.messages;

import bgu.spl.net.impl.BGRSServer.ConnectionHandler;
import bgu.spl.net.impl.BGRSServer.Message;

public class ERR implements Message {
    final int opcode=13;
    private int errorWith;

    public ERR(int error){
        this.errorWith=error;
    }

    @Override
    public Message process(ConnectionHandler handler) {
        return null;
    }

    @Override
    public byte[] encode() {
        return ("ERR "+errorWith+"\0").getBytes();
    }
}
