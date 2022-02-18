/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.util;

import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Nelore
 */
public class DefinePoints {

    //Define os pontos chaves do áudio
    public final int[] RANGE = new int[]{80, 120, 180, 300};

    public DefinePoints() {
    }

    public long[] getHash(Capture capture) {
        if (capture == null) {
        } else {
            //Passo 1: pegar os KeyPoints do audio gravado
            Complex[][] audioRecorded = this.convertToComplex(capture.getByteArrayOutputStream().toByteArray());
            int[][] kPointsRecorded = this.getKeyPoints(audioRecorded);
            long hashPoints[] = new long[kPointsRecorded.length];

            for (int i = 0; i < kPointsRecorded.length; i++) {
                hashPoints[i] = this.hash(kPointsRecorded[i]);
            }

            return hashPoints;
        }
        return null;
    }

    private Complex[][] convertToComplex(byte[] a) {
        byte audio[] = a;       //works
        final int size = audio.length;
        int amountPossible = size / 4096;
        Complex results[][] = new Complex[amountPossible][];

        for (int times = 0; times < amountPossible; times++) {
            Complex[] complex = new Complex[4096];
            for (int i = 0; i < 4096; i++) {
                complex[i] = new Complex(audio[(4096 * times) + i], 0);
            }
            results[times] = this.fft(complex);

        }
        return results;
    }

    private static Complex[] fft(Complex[] x) {
        int N = x.length;

        // base case
        if (N == 1) {
            return new Complex[]{x[0]};
        }

        // radix 2 Cooley-Tukey FFT
        if (N % 2 != 0) {
            throw new RuntimeException("N is not a power of 2");
        }

        // fft of even terms
        Complex[] even = new Complex[N / 2];
        for (int k = 0; k < N / 2; k++) {
            even[k] = x[2 * k];
        }
        Complex[] q = fft(even);

        // fft of odd terms
        Complex[] odd = even;  // reuse the array
        for (int k = 0; k < N / 2; k++) {
            odd[k] = x[2 * k + 1];
        }
        Complex[] r = fft(odd);

        // combine
        Complex[] y = new Complex[N];
        for (int k = 0; k < N / 2; k++) {
            double kth = -2 * k * Math.PI / N;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));

            y[k] = DefinePoints.plus(q[k], (DefinePoints.times(wk, r[k])));
            y[k + N / 2] = q[k].subtract(DefinePoints.times(wk, r[k]));
        }
        return y;
    }

    private int[][] getKeyPoints(Complex[][] results) {

        int linha;
        double[][] highscores = new double[results.length][4];
        int[][] points = new int[results.length][4];
        for (int i = 0; i < highscores.length; i++) {
            for (int j = 0; j < highscores[i].length; j++) {
                highscores[i][j] = 0.0;
            }
        }

        for (linha = 0; linha < results.length; linha++) {
            for (int freq = 40; freq < 300; freq++) {
                // Get the magnitude:
                double mag = Math.log(results[linha][freq].abs() + 1);

                // Find out which range we are in:
                int index = getIndex(freq); // index varia de 1-4

                // Save the highest magnitude and corresponding frequency:
                if (mag > highscores[linha][index]) {
                    highscores[linha][index] = mag;
                    points[linha][index] = freq;
                }
            }
        }
        return points;
    }

    private int getIndex(int freq) {
        //public final int[] RANGE = new int[]{40, 80, 120, 180, 300};
        int i = 0;
        while (RANGE[i] < freq) {
            i++;
        }
        return i;
    }

    private long hash(int points[]) {
        int FUZ_FACTOR = 2;

        long p3 = (long) points[3];
        long p2 = (long) points[2];
        long p1 = (long) points[1];
        long p0 = (long) points[0];

        long hash = (p3 - (p3 % FUZ_FACTOR)) * (long) 100000000
                + (p2 - (p2 % FUZ_FACTOR)) * (long) 100000
                + (p1 - (p1 % FUZ_FACTOR)) * (long) 100
                + (p0 - (p0 % FUZ_FACTOR));
        return hash;
    }

    public static Complex times(Complex a, Complex b) {
        Complex c = new Complex(a.getReal() * b.getReal(), a.getImaginary() * b.getImaginary());
        return c;
    }

    public static Complex plus(Complex a, Complex b) {
        double re = a.getReal() * Math.cos(a.getImaginary()) + b.getReal() * Math.cos(b.getImaginary());
        double im = a.getReal() * Math.sin(a.getImaginary()) + b.getReal() * Math.sin(b.getImaginary());
        Complex c = new Complex(re, im);
        return c;
    }

}
