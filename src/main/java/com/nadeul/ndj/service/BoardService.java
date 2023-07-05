package com.nadeul.ndj.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	/*
  private final PostsRepository postsRepository;
  private final UserRepository userRepository;

  @Transactional
  public Long save(PostsDto.Request dto, String nickname) {
      User user = userRepository.findByNickname(nickname);
      dto.setUser(user);
      log.info("PostsService save() 실행");
      Posts posts = dto.toEntity();
      postsRepository.save(posts);

      return posts.getId();
  }

  @Transactional(readOnly = true)
  public PostsDto.Response findById(Long id) {
      Posts posts = postsRepository.findById(id).orElseThrow(() ->
              new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

      return new PostsDto.Response(posts);
  }

  @Transactional
  public void update(Long id, PostsDto.Request dto) {
      Posts posts = postsRepository.findById(id).orElseThrow(() ->
              new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

      posts.update(dto.getTitle(), dto.getContent());
  }

  @Transactional
  public void delete(Long id) {
      Posts posts = postsRepository.findById(id).orElseThrow(() ->
              new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

      postsRepository.delete(posts);
  }

  @Transactional
  public int updateView(Long id) {
      return postsRepository.updateView(id);
  }


  @Transactional(readOnly = true)
  public Page<Posts> pageList(Pageable pageable) {
      return postsRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Page<Posts> search(String keyword, Pageable pageable) {
      Page<Posts> postsList = postsRepository.findByTitleContaining(keyword, pageable);
      return postsList;
  }
  */
}
