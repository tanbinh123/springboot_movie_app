package me.weekbelt.movieapp.domain.fileInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.movieapp.domain.BaseTimeEntity;

import javax.persistence.*;

@NoArgsConstructor @Getter
@Entity
public class FileInfo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String fileName;

    @Column(nullable = false, length = 255)
    private String saveFileName;

    @Column(nullable = false, length = 255)
    private String contentType;

    @Builder
    public FileInfo(String fileName, String saveFileName, String contentType) {
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.contentType = contentType;
    }
}
