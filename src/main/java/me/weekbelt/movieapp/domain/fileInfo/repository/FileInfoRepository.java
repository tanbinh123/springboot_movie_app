package me.weekbelt.movieapp.domain.fileInfo.repository;

import me.weekbelt.movieapp.domain.fileInfo.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<Long, FileInfo> {
}
