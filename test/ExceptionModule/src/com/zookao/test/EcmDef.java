package com.zookao.test;

/**
 * User: czc
 * Date: 2021-09-26
 */
public class EcmDef {
    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        int j = Integer.parseInt(args[1]);
        int result;
        try {
            result = ecm(i,j);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } catch (EcDef e) {
            System.out.println(e.getMessage());
        }
    }

    public static int ecm(int i,int j) throws EcDef {
        if(i < 0 || j < 0){
            throw new EcDef("不能为负值");
        }
        return i / j;
    }
}

class EcDef extends Exception{

    static final long serialVersionUID = -3387116993124229948L;

    public EcDef(){}

    public EcDef(String msg){
        super(msg);
    }
}
