package com.takue.leaguetable.repository;

import com.takue.leaguetable.model.Match;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class MatchRepository implements JpaRepository<Match, Long> {
  public void flush() {

  }

  public <S extends Match> S saveAndFlush(S entity) {
    return null;
  }

  public <S extends Match> List<S> saveAllAndFlush(Iterable<S> entities) {
    return List.of();
  }

  public void deleteAllInBatch(Iterable<Match> entities) {

  }

  public void deleteAllByIdInBatch(Iterable<Long> longs) {

  }

  public void deleteAllInBatch() {

  }

  public Match getOne(Long aLong) {
    return null;
  }

  public Match getById(Long aLong) {
    return null;
  }

  public Match getReferenceById(Long aLong) {
    return null;
  }

  public <S extends Match> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  public <S extends Match> List<S> findAll(Example<S> example) {
    return List.of();
  }

  public <S extends Match> List<S> findAll(Example<S> example, Sort sort) {
    return List.of();
  }

  public <S extends Match> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  public <S extends Match> long count(Example<S> example) {
    return 0;
  }

  public <S extends Match> boolean exists(Example<S> example) {
    return false;
  }

  public <S extends Match, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }

  public <S extends Match> S save(S entity) {
    return null;
  }

  public <S extends Match> List<S> saveAll(Iterable<S> entities) {
    return List.of();
  }

  public Optional<Match> findById(Long aLong) {
    return Optional.empty();
  }

  public boolean existsById(Long aLong) {
    return false;
  }

  public List<Match> findAll() {
    return List.of();
  }

  public List<Match> findAllById(Iterable<Long> longs) {
    return List.of();
  }

  public long count() {
    return 0;
  }

  public void deleteById(Long aLong) {

  }

  public void delete(Match entity) {

  }

  public void deleteAllById(Iterable<? extends Long> longs) {

  }

  public void deleteAll(Iterable<? extends Match> entities) {

  }

  public void deleteAll() {

  }

  public List<Match> findAll(Sort sort) {
    return List.of();
  }

  public Page<Match> findAll(Pageable pageable) {
    return null;
  }
}
