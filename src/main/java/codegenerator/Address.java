package codegenerator;

/**
 * Created by mohammad hosein on 6/28/2015.
 */
public class Address {
    private int num;
    private TypeAddress Type;
    private VarType varType;

    public Address(int num, VarType varType, TypeAddress Type) {
        this.num = num;
        this.Type = Type;
        this.varType = varType;
    }

    public Address(int num, VarType varType) {
        this.num = num;
        this.Type = TypeAddress.Direct;
        this.varType = varType;
    }

    public String toString() {

        if (Type == TypeAddress.Direct) {
            return num + "";
        }
        if (Type == TypeAddress.Indirect) {
            return "@" + num;
        }
        if (Type == TypeAddress.Imidiate) {
            return "#" + num;
        }

        return num + "";
    }

    public int getNum() {
        return num;
    }

    public TypeAddress getType() {
        return Type;
    }

    public codegenerator.VarType getVarType() {
        return varType;
    }
}
