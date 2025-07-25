package kcanmin.com.gpcgpt.service;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptServiceImpl implements GptService{

  private final OkHttpClient client = new OkHttpClient();

  @Value("${openai.api-key}")
  private String apiKey;

  @Value("${openai.model}")
  private String model;

  @Value("${openai.prompt-template}")
  private String promptTemplate;

  @Override
  public String summarize(String commitMessage, String diff) {
    String prompt = String.format(promptTemplate, commitMessage, diff);

    JSONObject body = new JSONObject()
            .put("model", model)
            .put("messages", new JSONObject[] {
                    new JSONObject()
                            .put("role", "user")
                            .put("content", prompt)
            });

    Request request = new Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Authorization", "Bearer " + apiKey)
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(
                    body.toString(),
                    MediaType.parse("application/json")
            ))
            .build();

    try (Response response = client.newCall(request).execute()) {
      String result = response.body().string();
      JSONObject json = new JSONObject(result);
      return json
              .getJSONArray("choices")
              .getJSONObject(0)
              .getJSONObject("message")
              .getString("content")
              .trim();
    } catch (IOException e) {
      throw new RuntimeException("GPT 요약 실패", e);
    }
  }

}
