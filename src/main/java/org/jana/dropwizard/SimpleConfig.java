package org.jana.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import java.util.Objects;

public class SimpleConfig extends Configuration {

    private DataSourceFactory dataSourceFactory;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        if (Objects.isNull(dataSourceFactory))
            this.dataSourceFactory = new DataSourceFactory();
        return dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

}


