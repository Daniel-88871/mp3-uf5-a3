package model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Activitat {
    @CsvBindByName
    String codi;
    @CsvBindByName
    String data_fi;
    @CsvBindByName
    String data_inici;
    @CsvBindByName
    String denominaci;
}