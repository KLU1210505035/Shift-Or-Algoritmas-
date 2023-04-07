import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//1210505035 Emirhan Şimşek Shift Or Algorithm With Alice In Wonderland
public class ShiftOr {
    public static void main(String[] args) {

        String dosyaAdi = "C:\\Users\\Lenovo\\OneDrive\\Masaüstü\\alice_in_wonderland.txt"; // Okunacak Dosyanın Yolunu Veriyoruz
        String[] kelimeler = {"upon", "sigh", "Dormouse", "jury-box", "swim"};
        Map<String, Integer> kelimeSayisi = new HashMap<>();

        // Dosyadaki Her Bir Kelimeyi Sayan Kod Parçası
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String kelime : kelimeler) {
                    int count = countOccurrences(line, kelime);
                    kelimeSayisi.put(kelime, kelimeSayisi.getOrDefault(kelime, 0) + count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Kullanıcıdan istenilen kelimeyi dosyada aratmak için yazdığım kod parçası
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dosyadan Kaç Kere Geçtiğini Öğrenmek İstediğin Kelime: ");
        String input = scanner.nextLine();
        int inputCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String line;

            while ((line = reader.readLine()) != null) {
                int count = countOccurrences(line, input);
                inputCount += count;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //Hem Var Olan Kelimelerin Hemde Yeni Girilen Kelimeninde Dosyada Kaç Adet Olduğunu Bulur Ekrana Yazdırır.
        System.out.print("\nSabit Verilen Kelimeler Dosyada Kere Geçiyor: \n");
        for (String kelime : kelimeler) {
            int count = kelimeSayisi.getOrDefault(kelime, 0);
            System.out.println(kelime + " Kelimesi " + count +" Geçiyor. ");
        }
        System.out.println("\nKullanıcıdan Alınan '" + input + "' Kelimesi Dosya İçerisinden " + inputCount +" Geçiyor. ");
    }

    private static int countOccurrences(String text, String pattern) {
        int count = 0;
        int n = text.length();
        int m = pattern.length();
        int[] mask = new int[256];
        for (int i = 0; i < m; i++) {
            mask[pattern.charAt(i)] |= 1 << i;
        }
        int state = 0;
        for (int i = 0; i < n; i++) {
            state = ((state << 1) | 1) & mask[text.charAt(i)];
            if ((state & (1 << (m - 1))) != 0) {
                count++;
            }
        }
        return count;
    }
}