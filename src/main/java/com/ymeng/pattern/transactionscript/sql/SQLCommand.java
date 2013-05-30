package com.ymeng.pattern.transactionscript.sql;

final class SQLCommand {

    public static final String FIND_RECOGNITIONS_STATEMENT =
            "SELECT amount"
                    + " FROM revenue_recognition"
                    + " WHERE contract = ? AND recognized_on <= ?";

    public static final String FIND_CONTRACT_STATEMENT =
            "SELECT revenue, date_signed, type"
                    + " FROM contract INNER JOIN product ON contract.product = product.id"
                    + " WHERE contract.id = ?";

    private SQLCommand() {
    }
}
