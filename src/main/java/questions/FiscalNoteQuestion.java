package questions;

public class FiscalNoteQuestion extends Question {
    private final String uf;
    private final String year;
    private final String month;
    private final String cnpj;
    private final String model;
    private final String number;
    private final String serie;
    private final String numericCode;
    private final String verifyDigit;

    public FiscalNoteQuestion(int num, String uf, String year, String month, String cnpj, String model, String serie, String number, String numericCode, String verifyDigit, String title, int[] options, int rightAnswer) {
        super(num, title, options, rightAnswer);
        this.uf = uf;
        this.year = year;
        this.month = month;
        this.cnpj = cnpj;
        this.model = model;
        this.serie = serie;
        this.number = number;
        this.numericCode = numericCode;
        this.verifyDigit = verifyDigit;
    }

    public String getUf() {
        return uf;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getModel() {
        return model;
    }

    public String getSerie() {
        return serie;
    }

    public String getNumber() {
        return number;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public String getVerificatorDigit() {
        return verifyDigit;
    }

}
