///-----------------------------------------------------------------------///
/// @copyright (c) 2021 by Roman Berngardt. All rights are absolutely not ///
/// reserved.                                                             ///
///                                                                       ///
/// PLEASE FEEL FREE TO ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE    ///
/// HEADER AT ALL.                                                        ///
///                                                                       ///
/// This code is free software; you can redistribute it and/or modify it  ///
/// without any restrictions.                                             ///
///                                                                       ///
/// This code is distributed as a part of home work (practicum task, unit ///
/// 9) in the hope that it will work correctly and will be useful, but    ///
/// WITHOUT ANY WARRANTY; without even the implied warranty of            ///
/// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                  ///
/// JUST ENJOY IT! :)                                                     ///
///-----------------------------------------------------------------------///
/// File created on: 2021-12-20                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

import java.io.*;

/**
 * The {@code ByteInputStream} class contains implementation of
 * a simple input stream, which generates the byte values
 * in range of 0..255 as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
class ByteInputStream extends InputStream {
    // The minimum value of the range
    private final int RANGE_MIN_VALUE = 0;
    // The maximum value of the range
    private final int RANGE_MAX_VALUE = 255;

    /**
     * @return Returns the random value in range 0..255
     * @throws IOException
     */
    @Override
    public int read() throws IOException {
        return getRandomNumber(RANGE_MIN_VALUE, RANGE_MAX_VALUE);
    }

    /**
     * @brief Genearte a random value in range min..max
     * @param min - The minimum value of the range
     * @param max - The maximum value of the range
     * @return The random value in range min..max
     */
    private byte getRandomNumber(int min, int max) {
        return (byte) ((Math.random() * (max - min)) + min);
    }
}


/**
 * The {@code ByteInputStreamReader} class contains implementation of
 * a simple input stream reader as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
class ByteInputStreamReader extends InputStreamReader {

    /**
     * @brief The constructor ot the class
     * @param in - The input stream
     */
    public ByteInputStreamReader(InputStream in) {
        super(in);
    }

    /**
     * @brief The method to read a "length" bytes
     * @param data - The byte array for the data
     * @param length - The count of bytes to be read
     * @throws IOException
     */
    public int read(byte[] data, int length) throws IOException {
        int cnt = 0;
        while (cnt < length - 1) {
            data[cnt] = (byte)super.read();
            cnt++;
        }
        return ++cnt;
    }
}


/**
 * The {@code DataFileWriter} class contains implementation of
 * a simple file wirter as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
class DataFileWriter extends FileWriter {

    /**
     * @brief The constructor
     * @param file - the file opbejct
     * @throws IOException
     */
    public DataFileWriter(File file) throws IOException {
        super(file);
    }

    /**
     * @param dataToWrite - the data which has to be written to the file
     * @throws IOException
     */
    public void write(byte[] dataToWrite) throws IOException {
        for (int i = 0; i < dataToWrite.length; i++) {
            int byteToWrite = (int)dataToWrite[i];
            if (byteToWrite < 0) byteToWrite = (-1*(~byteToWrite+1));
            super.write(byteToWrite);
        }
    }
}


/**
 * The {@code DataFileReader} class contains implementation of
 * a simple file reader as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
class DataFileReader extends FileReader {

    /**
     * @brief The constructor
     * @param file - the file object
     * @throws FileNotFoundException
     */
    public DataFileReader(File file) throws FileNotFoundException {
        super(file);
    }

    public int read(byte[] dataToRead, int length) throws IOException {
        for (int i = 0; i < length; i++) {
            int rData = super.read();
            if (rData > 128) rData -= 256;
            dataToRead[i] = (byte)(rData & 0xFF);
        }
        return length;
    }
}


/**
 * The {@code FileIOChecker} class contains implementation of
 * a simple file IO checker as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class FileIOChecker {
    static final int BUF_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        File dataFile = new File("./datafile.bin");
        // Check, if the file exists
        if (dataFile.exists()) {
            System.out.println("ИНФА: Файл '" + dataFile.getName() + "' уже существует -> удаляем!");
            // If the file exists -> delete it first.
            if (dataFile.delete()) {
                System.out.println("ИНФА: Файл '" + dataFile.getName() + "' успешно удалён!");
            } else {
                System.err.println("ОШИБКА: Проблема с ужадением файла '" + dataFile.getName() + "'!");
            }
        }

        try {
            if (dataFile.createNewFile()) {
                System.out.println("ИНФА: Файл '" + dataFile.getName() + "' успешно создан!");
                try (
                        ByteInputStreamReader bisr = new ByteInputStreamReader(new ByteInputStream());
                        DataFileWriter dataFileWriter = new DataFileWriter(dataFile);
                        DataFileReader dataFileReader = new DataFileReader(dataFile);
                ) {
                    byte[] dataToWrite = new byte[BUF_SIZE];
                    if (dataToWrite.length == bisr.read(dataToWrite, dataToWrite.length)) {
                        System.out.println("ИНФА: Данные успрешно сгенерированы!");
                        // Write the test data to the file
                        dataFileWriter.write(dataToWrite);
                        // Read the data from the file back
                        byte[] dataToAnalyse = new byte[BUF_SIZE];
                        dataFileReader.read(dataToAnalyse, BUF_SIZE);

                        // Check, if the data match
                        boolean isDataMatch = true;
                        for (int i = 0; i < BUF_SIZE; i++) {
                            if (dataToWrite[i] != dataToAnalyse[i]) {
                                System.out.println("i="+i+" w="+dataToWrite[i]+" r="+dataToAnalyse[i]);
                                isDataMatch = false;
                                break;
                            }
                        }

                        // Print out the result
                        if (isDataMatch) {
                            System.out.println("ИНФА: Записанные и прочитанные данные совпадают!");
                        } else {
                            System.err.println("ОШИБКА: Записанные и прочитанные данные НЕ совпадают!");
                        }
                    } else {
                        System.err.println("ОШИБКА: Проблема при генерации данных!");
                    }
                } catch (IOException exc) {
                    System.err.println("ОШИБКА: Проблема при работе с файлом '" + dataFile.getName() + "'!");
                }
            }
        } catch (IOException exc) {
            System.err.println("ОШИБКА: Проблема при создании файла '" + dataFile.getName() + "'!");
        }
    }
}
