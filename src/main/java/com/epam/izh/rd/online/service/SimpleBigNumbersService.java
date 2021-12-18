package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     *
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        return new BigDecimal(a).divide(new BigDecimal(b), range, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger primaryNumber = new BigInteger("1");
        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");
        int beforeRange = 0;
        boolean isDelimetr;
        while (beforeRange <= range) {
            isDelimetr = true;
            primaryNumber = primaryNumber.add(one);
            for (BigInteger i = new BigInteger("2"); i.compareTo(primaryNumber) < 0; i = i.add(one)) {
                if (primaryNumber.remainder(i).compareTo(zero) == 0) {
                    isDelimetr = false;
                    break;
                }
            }
            if (isDelimetr) {
                beforeRange++;
            }

        }
        return primaryNumber;
    }
}
