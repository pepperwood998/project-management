package com.tuan.exercise.projman.util;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.tuan.exercise.projman.config.Constant;

public class GeneralIdentifierGenerator implements IdentifierGenerator {
    private Random random = new Random();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {

        String result;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < Constant.getIdLength()) {
            sb.append(Integer.toHexString(random.nextInt()));
        }
        result = sb.substring(0, Constant.getIdLength());

        return result;
    }

}
