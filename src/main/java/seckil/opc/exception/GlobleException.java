package seckil.opc.exception;

import seckil.opc.Result.CodeMsg;

public class GlobleException  extends RuntimeException{

    private CodeMsg codeMsg;

    public GlobleException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }
}
