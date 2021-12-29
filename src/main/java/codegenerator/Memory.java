package codegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammad hosein on 6/27/2015.
 */
public class Memory {

    private List<ThreeAddressCode> codeBlock;
    private int lastTempIndex;
    private int lastDataAddress;
    private static int stratTempMemoryAddress = 500;
    private static int stratDataMemoryAddress = 200;
    private static int dataSize = 4;
    private static int tempSize = 4;

    public Memory() {
        codeBlock = new ArrayList<ThreeAddressCode>();
        lastTempIndex = stratTempMemoryAddress;
        lastDataAddress = stratDataMemoryAddress;
    }

    public int getTemp() {
        lastTempIndex += tempSize;
        return lastTempIndex - tempSize;
    }

    public int getDateAddress() {
        lastDataAddress += dataSize;
        return lastDataAddress - dataSize;
    }

    public int saveMemory() {
        codeBlock.add(new ThreeAddressCode());
        return codeBlock.size() - 1;
    }

    public void add3AddressCode(Operation op, Address opr1, Address opr2, Address opr3) {
        codeBlock.add(new ThreeAddressCode(op, opr1, opr2, opr3));
    }

    public void add3AddressCode(int i, Operation op, Address opr1, Address opr2, Address opr3) {
        codeBlock.remove(i);
        codeBlock.add(i, new ThreeAddressCode(op, opr1, opr2, opr3));
    }


    public int getCurrentCodeBlockAddress() {
        return codeBlock.size();
    }

    public void pintCodeBlock() {
        System.out.println("Code Block");
        for (int i = 0; i < codeBlock.size(); i++) {
            System.out.println(i + " : " + codeBlock.get(i).toString());
        }
    }
}

class ThreeAddressCode {
    public Operation operation;
    public Address Operand1;
    public Address Operand2;
    public Address Operand3;

    public ThreeAddressCode() {

    }

    public ThreeAddressCode(Operation op, Address opr1, Address opr2, Address opr3) {
        operation = op;
        Operand1 = opr1;
        Operand2 = opr2;
        Operand3 = opr3;
    }

    public String toString() {
        if (operation == null) {
            return "";
        }
        StringBuffer res = new StringBuffer("(");
        res.append(operation.toString()).append(",");
        if (Operand1 != null) {
            res.append(Operand1);
        }
        res.append(",");
        if (Operand2 != null) {
            res.append(Operand2);
        }
        res.append(",");
        if (Operand3 != null) {
            res.append(Operand3);
        }
        res.append(")");

        return res.toString();
    }
}
