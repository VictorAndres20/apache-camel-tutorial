package com.vitisystems.apachecameltutorial.example1.pojos;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"messages",
"result"
})
public class RestResponse {

    @JsonProperty("messages")
    private List<String> messages = null;
    @JsonProperty("result")
    private List<Result> result = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("messages")
    public List<String> getMessages() {
    return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<String> messages) {
    this.messages = messages;
    }

    @JsonProperty("result")
    public List<Result> getResult() {
    return result;
    }

    @JsonProperty("result")
    public void setResult(List<Result> result) {
    this.result = result;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    }
    
}
