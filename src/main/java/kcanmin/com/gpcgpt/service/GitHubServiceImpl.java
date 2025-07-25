package kcanmin.com.gpcgpt.service;

import kcanmin.com.gpcgpt.domain.dto.CommitDto;

import java.util.List;

public class GitHubServiceImpl implements GitHubService {

  private static final OkHttpClient client = new OkHttpClient();
  private static final String GITHUB_API = "https://api.github.com";

  @Override
  public List<CommitDto> getRecentCommits(String repoUrl) {
    String[] parts = extractOwnerRepo(repoUrl);
    String url = GITHUB_API + "/repos/" + parts[0] + "/" + parts[1] + "/commits";

    Request request = new Request.Builder()
            .url(url)
            .header("Accept", "application/vnd.github+json")
            .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("GitHub API 실패");

      String body = response.body().string();
      JSONArray arr = new JSONArray(body);
      List<CommitDto> list = new ArrayList<>();

      for (int i = 0; i < Math.min(5, arr.length()); i++) {
        JSONObject obj = arr.getJSONObject(i);
        JSONObject commit = obj.getJSONObject("commit");
        JSONObject author = commit.getJSONObject("author");

        list.add(CommitDto.builder()
                .hash(obj.getString("sha"))
                .msg(commit.getString("message"))
                .author(author.getString("name"))
                .cName(author.getString("name"))
                .build());
      }

      return list;

    } catch (Exception e) {
      log.error("getRecentCommits 오류: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getCommitDiff(String repoUrl, String sha) {
    String[] parts = extractOwnerRepo(repoUrl);
    String url = GITHUB_API + "/repos/" + parts[0] + "/" + parts[1] + "/commits/" + sha;

    Request request = new Request.Builder()
            .url(url)
            .header("Accept", "application/vnd.github.v3.diff")
            .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("diff 요청 실패");
      return response.body().string();
    } catch (Exception e) {
      log.error("getCommitDiff 오류: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  private String[] extractOwnerRepo(String repoUrl) {
    // ex: https://github.com/DaHnDell/Nocean
    String[] parts = repoUrl.replace("https://github.com/", "").split("/");
    if (parts.length != 2) throw new IllegalArgumentException("잘못된 GitHub URL 형식");
    return parts;
  }
}
