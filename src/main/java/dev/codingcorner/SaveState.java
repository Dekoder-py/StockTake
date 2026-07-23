package dev.codingcorner;

import java.io.File;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Vector;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SaveState {

  private String filePath;

  public SaveState() {
    this.filePath = Path.of(
        System.getProperty("user.home"),
        ".stocktake",
        "stocktake.json").toString();
  };

  public void saveToFile(Vector<InventoryItem> items) {
    File file = new File(filePath);

    File parent = file.getParentFile();
    if (parent != null) {
        parent.mkdirs();
    }

    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.writeValue(file, items);
    } catch (StreamWriteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DatabindException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public Vector<InventoryItem> loadFromFile() {
    File file = new File(filePath);
    if (!file.exists()) {
      return new Vector<>();
    }

    ObjectMapper mapper = new ObjectMapper();
    try {
      Vector<InventoryItem> items = mapper.readValue(file, new TypeReference<Vector<InventoryItem>>() {
      });
      return items;
    } catch (Exception e) {
      e.printStackTrace();
      return new Vector<>();
    }

  }
}
