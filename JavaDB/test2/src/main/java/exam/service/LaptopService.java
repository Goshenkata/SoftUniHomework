package exam.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

//ToDo - Implement all methods
public interface LaptopService {
    boolean areImported();

    String readLaptopsFileContent() throws IOException;

    String importLaptops() throws IOException;

    String exportBestLaptops();
}
