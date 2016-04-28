package WindowsService;

public class Request implements java.io.Serializable{

  private static final long serialVersionUID = 1L;

  private String rq_system_id ;
  private int rq_token_id;

  public Request() {
  }

  public Request(int rq_token_id, String rq_system_id) {
    this.rq_token_id = rq_token_id;
    this.rq_system_id = rq_system_id;
  }

  public String getrq_system_id() {
    return this.rq_system_id;
  }
  
  public int getrq_token_id() {
    return this.rq_token_id;
  }

  public void setrq_token_id(Integer tokenId) {
    this.rq_token_id = tokenId;
  }
  
  public void setrq_system_id(String systemId) {
    this.rq_system_id = systemId;
  }
}
