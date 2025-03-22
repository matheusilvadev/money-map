package tech.astrocode.web_backend.domain.type;

public enum ActivityType {
    REVENUE("revenue"),
    EXPENSE("expense");

    private String value;

    ActivityType(String aValue){
        this.value = aValue;
    }

    public String getValue(){
        return this.value;
    }

}
