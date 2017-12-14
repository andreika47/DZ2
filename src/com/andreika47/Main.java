package com.andreika47;


public class Main
{
    public static int DEFAULT_PASSWORD_LENGTH = 10;

    public static class PasswordGenerator
    {
        public static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890";

        // Генерация одного символа пароля
        public static char getRandomChar()
        {
            char index = 0;

            for(int i = 0; i < 6; i++)             // Получения индекса, засчёт шестикратного взятия последнего бита System.nanoTime()
            {
                if((System.nanoTime() & 1L) == 1)
                {
                    index |= (1 << i);
                }
            }
            return ALPHABET.charAt(index % ALPHABET.length()); // Взятие символа алфавита по индексу, так как index может быть больше длины алфваит берем остаток
        }

        // Генерация пароля длины length
        public static String generatePassword(int length)
        {
            char[] result = new char[length];

            for(int i = 0; i < length; i++)
            {
                result[i] = getRandomChar();
            }
            return new String(result);
        }
    }

    public static void main(String[] args)
    {
        int passwordLength = DEFAULT_PASSWORD_LENGTH;

        try
        {
            // Получения длины пароля из первого аргумента командной строки.
            // Если первый аргумент отсутвует или не является положительным числом, то генерируется пароль длины DEFAULT_PASSWORD_LENGTH
            if(args.length > 0)
            {
                passwordLength = Integer.parseInt(args[0]);
                if(passwordLength <= 0)
                    passwordLength = DEFAULT_PASSWORD_LENGTH;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println(e);
        }
        System.out.println(PasswordGenerator.generatePassword(passwordLength));
    }
}
