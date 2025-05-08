package model;

public class State {
    private Integer IdState;
    private String state;

    public State() {
    }

    public State(Integer idState, String state) {
        IdState = idState;
        this.state = state;
    }

    public Integer getIdState() {
        return IdState;
    }

    public void setIdState(Integer idState) {
        IdState = idState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
