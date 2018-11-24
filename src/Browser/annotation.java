package Browser;


public class Annotation {
    private String type;
    private String label;
    private String id;
    private String start;
    private String end;
    private String agreement;
    private String content;

    public Annotation(String type, String label,String id,String start, String end,String agreement,String content) {
        this.type = type;
        this.label = label;
        this.id = id;
        this.start = start;
        this.end = end;
        this.agreement = agreement;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }
    
    public String getId() {
    	return id	;
    }
    
    public String getStart() {
    	return start;
    }
    
    public String getEnd() {
    	return end;
    }
    
    public String getAgreement() {
    	return agreement;
    }
    public String getContent() {
    	return content;
    }
}
