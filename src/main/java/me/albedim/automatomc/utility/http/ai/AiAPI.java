package me.albedim.automatomc.utility.http.ai;

import com.google.gson.JsonObject;
import me.albedim.automatomc.AuTomatoMC;
import me.albedim.automatomc.utility.aichat.AIChat;
import me.albedim.automatomc.utility.http.ai.ai_schema.AiSchema;
import me.albedim.automatomc.utility.http.controller.HttpController;

public class AiAPI {

    public static String getResponse(AIChat chat)
    {
        JsonObject res = HttpController.post(
                "/completions",
                AuTomatoMC.getInstance().getConfigurationParam("groq_api_key"),
                new AiSchema(chat.getMessages()),
                JsonObject.class
        ).getAsJsonObject();

        if (res.has("code") && res.get("code").getAsInt() != 200) {
            return AuTomatoMC.getInstance().getConfigurationParam("error_message");
        }

        int messages = res.getAsJsonObject().get("choices").getAsJsonArray().size();
        String response = res.getAsJsonObject()
                .get("choices").getAsJsonArray().get(messages - 1).getAsJsonObject()
                .get("message").getAsJsonObject()
                .get("content").getAsString();
        return response;
    }

}
