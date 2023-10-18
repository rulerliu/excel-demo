package com.mayikt.vo;

import io.minio.ObjectStat;
import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder
public class ObjectStatVO implements AutoCloseable {

    private ObjectStat stat;
    private InputStream inputStream;

    @Override
    public void close() throws Exception {
        if (this.inputStream != null) {
            this.inputStream.close();
        }
    }

}
