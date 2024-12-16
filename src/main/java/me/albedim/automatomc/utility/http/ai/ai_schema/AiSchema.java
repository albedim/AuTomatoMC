package me.albedim.automatomc.utility.http.ai.ai_schema;

import java.util.ArrayList;

public class AiSchema {

    private String model;
    private ArrayList<MessageSchema> messages;

    public AiSchema(ArrayList<MessageSchema> messages) {
        this.model = "llama3-8b-8192";
        this.messages = messages;
    }
}