package com.example.alperkaya.hexremote;

public class CanMessage {

    private int extID;
    private byte[] canData = new byte[8];

    public CanMessage(){}

    public CanMessage(int extID, byte[] canData){
        this.extID = extID;
        this.canData = canData;
    }

    public CanMessage(int extID, int data0, int data1,
                      int data2, int data3, int data4,
                      int data5, int data6, int data7){
        this.extID = extID;
        canData[0] = (byte) data0;
        canData[1] = (byte) data1;
        canData[2] = (byte) data2;
        canData[3] = (byte) data3;
        canData[4] = (byte) data4;
        canData[5] = (byte) data5;
        canData[6] = (byte) data6;
        canData[7] = (byte) data7;

    }

    public void setExtID(int extID){
        this.extID = extID;
    }

    public int getExtID(){
        return this.extID;
    }

    public void setCanData(byte[] canData){
        this.canData = canData;
    }

    public byte[] getCanData(){
        return this.canData;
    }

    public byte getCanByte(int position){
        return this.canData[position];
    }

}

