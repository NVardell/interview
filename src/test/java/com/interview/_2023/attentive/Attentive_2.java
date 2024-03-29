package com.interview._2023.attentive;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - Top Stocks
 * PROBLEM
 *      - Given a list of stocks & prices, return the 3 top stocks.
 *      - Method should return an array containing the names of the
 *        three stocks with the highest average value.
 *          + The array should be sorted by decreasing average value.
 *      - Guaranteed that every stock will have a unique average.
 * INPUTS
 *      - An array of strings (stocks), representing the considered stocks.
 *      - An array of 2 dimensions (prices), representing the stock prices
 *        (inner lists) for each day (outer lists).
 * EXAMPLE #1
 *      In ~ Stocks = [ AMZN, GOOG, ORLY, ULTA ]
 *           Prices = [[ 1.11, 2.2, 3.333, 4.4],
 *                     [ 1.11, 2.2, 3.333, 4.4],
 *                     [ 1.11, 2.2, 3.333, 4.4]]
 *      Out ~ [ ULTA, ORLY, GOOG ]
 *
 * @author NV
 * @since 1/10/2024
 */
public class Attentive_2 {

    private String[] solution(String[] stocks, float[][] prices) {
        // Array lengths
        int stockCount = stocks.length;
        int priceCount = prices.length;

        // Top 3 Stocks (Return)
        String[] topThree = new String[3];

        // Highest avg stock values
        float first=0f, second=0f, third=0f;

        // Aggregated stock prices
        float[] stockPrice = new float[stockCount];

        // Create 1d array with summed stock prices
        for(int i=0; i<prices.length; i++){
            for(int j=0; j<prices[i].length; j++) {
                stockPrice[j]+=prices[i][j];
            }
        }

        // Get average price by dividing sum by # of prices & set top 3
        for(int k=0; k<stockCount; k++) {
            stockPrice[k]/=priceCount;

            float temp = stockPrice[k];

            if(temp>first) {                    // Set highest & overwrite #2&3
                third = second;
                topThree[2] = topThree[1];

                second = first;
                topThree[1] = topThree[0];

                first = temp;
                topThree[0] = stocks[k];
            } else if(temp>second) {            // Set second highest & overwrite #3
                third = second;
                topThree[2] = topThree[1];

                second = temp;
                topThree[1] = stocks[k];
            } else if(temp>third) {             // Set third highest
                third = temp;
                topThree[2] = stocks[k];
            }
        }

        return topThree;
    }

    @Test void testSolution_one() {
        String[] stocks = new String[] { "AMZN",  "CACC",  "EQIX",  "GOOG",  "ORLY",  "ULTA" };
        float[][] prices = new float[][] {
                { 12.81f, 11.09f, 12.11f, 10.93f, 9.83f,  8.14f },
                { 10.34f, 10.56f, 10.14f, 12.17f, 13.1f,  11.22f },
                { 11.53f, 10.67f, 10.42f, 11.88f, 11.77f, 10.21f }
        };

        assertThat(solution(stocks, prices)[0], is("GOOG"));
        assertThat(solution(stocks, prices)[1], is("ORLY"));
        assertThat(solution(stocks, prices)[2], is("AMZN"));
    }

    @Test void testSolution_two() {
        String[] stocks = new String[] { "TTOO", "RILY", "CIZ", "SIEB", "BKCC", "CMBM", "ACNB", "CSQ", "COWN", "OTEL", "ALNA", "OSS", "CFFAW", "STRM", "GLMD", "BROGR", "SLRX", "GSUM", "LYFT", "BIDU", "WVVI", "APM", "EMXC", "PDP", "SPNS", "CSWC", "PFF", "MSVB", "AGZD", "HBIO", "IPAR", "BYND", "APPN", "VLGEA", "CNACR", "TDAC", "VNDA", "RILYI", "ENLV", "ADIL", "CXSE", "ONTX", "ONEQ", "VRTS", "PCTY", "TCMD", "MANT", "CVLT", "AMOV", "ADTN" };
        float[][] prices = new float[][] {
                { 141.57f, 126.51f, 135.09f, 136.85f, 166.07f, 138.88f, 164.22f, 134.11f, 136.73f, 113.62f, 118.5f, 134.88f, 150.0f, 164.92f, 189.01f, 207.89f, 142.57f, 145.88f, 170.55f, 114.48f, 113.92f, 142.23f, 162.2f, 156.37f, 188.58f, 102.25f, 168.59f, 146.44f, 182.72f, 139.31f, 118.6f, 115.92f, 110.74f, 101.46f, 162.42f, 150.29f, 166.95f, 114.64f, 172.82f, 167.49f, 189.97f, 140.58f, 173.13f, 200.75f, 130.3f, 140.07f, 148.48f, 120.76f, 226.87f, 156.49f },
                { 165.49f, 118.05f, 125.45f, 132.56f, 130.98f, 123.27f, 128.32f, 154.43f, 153.95f, 155.82f, 114.37f, 106.33f, 204.14f, 179.07f, 161.2f, 170.55f, 168.27f, 140.08f, 168.85f, 111.62f, 136.38f, 147.1f, 165.33f, 142.99f, 203.98f, 100.34f, 161.65f, 132.96f, 177.1f, 140.53f, 134.93f, 120.7f, 94.48f, 114.56f, 168.14f, 101.99f, 152.32f, 86.93f, 140.31f, 144.98f, 169.37f, 133.45f, 207.18f, 193.43f, 113.23f, 164.71f, 169.56f, 118.43f, 191.26f, 144.69f },
                { 138.46f, 139.0f, 114.42f, 147.3f, 145.87f, 154.6f, 153.24f, 153.21f, 142.59f, 125.81f, 120.4f, 134.53f, 190.23f, 166.4f, 168.75f, 164.7f, 154.94f, 155.22f, 187.83f, 119.89f, 137.44f, 168.32f, 164.35f, 163.77f, 202.43f, 104.6f, 147.92f, 164.29f, 181.87f, 156.0f, 130.71f, 130.43f, 107.7f, 113.43f, 159.21f, 170.36f, 149.33f, 96.92f, 144.8f, 139.81f, 188.4f, 158.9f, 178.41f, 194.34f, 130.39f, 168.88f, 183.55f, 124.75f, 167.72f, 167.23f },
                { 153.54f, 126.61f, 126.26f, 118.52f, 163.08f, 149.76f, 148.0f, 154.62f, 146.57f, 165.06f, 122.62f, 128.56f, 199.35f, 184.36f, 180.87f, 190.7f, 152.07f, 126.71f, 185.76f, 110.59f, 139.16f, 165.55f, 139.79f, 104.37f, 183.82f, 111.75f, 148.01f, 158.94f, 157.54f, 133.28f, 117.06f, 134.14f, 112.31f, 119.58f, 160.76f, 134.23f, 168.11f, 89.94f, 138.16f, 133.24f, 156.17f, 167.27f, 190.47f, 190.78f, 150.75f, 139.67f, 158.97f, 100.71f, 209.05f, 180.05f },
                { 148.58f, 110.36f, 122.47f, 145.95f, 168.45f, 152.8f, 154.33f, 164.82f, 155.14f, 165.08f, 106.24f, 142.86f, 199.79f, 129.11f, 158.02f, 175.05f, 149.73f, 137.24f, 189.86f, 109.72f, 128.51f, 168.89f, 144.08f, 157.44f, 189.78f, 109.89f, 122.97f, 133.87f, 205.65f, 139.45f, 110.18f, 102.09f, 84.07f, 112.84f, 163.4f, 117.69f, 186.41f, 95.94f, 167.82f, 118.33f, 170.16f, 154.35f, 184.33f, 168.87f, 150.48f, 168.5f, 167.26f, 82.47f, 201.1f, 156.56f },
                { 147.62f, 105.4f, 126.73f, 133.51f, 145.21f, 129.4f, 157.37f, 170.97f, 141.09f, 148.7f, 122.69f, 147.98f, 178.34f, 182.85f, 191.27f, 224.35f, 144.65f, 152.3f, 178.36f, 110.71f, 121.63f, 146.85f, 176.25f, 148.65f, 209.24f, 112.33f, 165.72f, 149.77f, 168.76f, 152.05f, 122.58f, 102.62f, 103.48f, 102.92f, 169.88f, 143.14f, 170.57f, 120.15f, 149.74f, 138.41f, 168.47f, 181.18f, 177.91f, 227.52f, 119.3f, 151.48f, 174.68f, 113.83f, 180.73f, 166.05f },
                { 169.42f, 111.18f, 112.63f, 154.95f, 165.75f, 157.65f, 160.98f, 147.54f, 139.94f, 166.3f, 105.29f, 119.6f, 171.74f, 168.12f, 189.15f, 182.81f, 175.84f, 155.68f, 173.55f, 135.38f, 143.43f, 163.99f, 149.74f, 141.99f, 222.11f, 105.9f, 141.29f, 159.01f, 159.23f, 130.43f, 111.07f, 132.18f, 99.72f, 104.4f, 160.16f, 143.26f, 178.39f, 93.01f, 163.84f, 143.65f, 153.53f, 166.36f, 185.01f, 179.71f, 121.21f, 151.33f, 131.24f, 114.4f, 193.83f, 155.55f },
                { 158.01f, 127.71f, 104.63f, 126.12f, 120.85f, 128.92f, 203.63f, 152.74f, 153.51f, 158.6f, 122.63f, 123.8f, 134.14f, 173.6f, 141.01f, 201.08f, 157.31f, 130.27f, 149.57f, 107.41f, 136.21f, 155.33f, 152.03f, 149.39f, 177.95f, 106.12f, 146.03f, 155.51f, 168.49f, 128.2f, 140.5f, 119.91f, 115.6f, 98.52f, 170.77f, 164.44f, 147.75f, 82.06f, 168.04f, 173.46f, 122.37f, 150.97f, 174.37f, 226.02f, 130.77f, 159.73f, 148.91f, 112.55f, 170.1f, 139.86f },
                { 144.59f, 138.0f, 105.41f, 136.69f, 174.51f, 153.69f, 190.36f, 113.96f, 161.61f, 155.65f, 90.07f, 132.96f, 174.81f, 179.82f, 155.1f, 195.7f, 156.01f, 146.7f, 169.5f, 124.89f, 106.82f, 154.06f, 153.54f, 139.03f, 210.78f, 95.03f, 165.93f, 139.96f, 188.48f, 127.37f, 126.02f, 126.34f, 98.34f, 113.15f, 151.21f, 171.97f, 197.34f, 82.71f, 168.39f, 138.52f, 162.56f, 166.84f, 171.59f, 152.93f, 100.62f, 120.34f, 141.61f, 100.19f, 201.94f, 168.22f },
                { 153.44f, 96.63f, 86.03f, 107.38f, 133.62f, 165.35f, 183.12f, 143.64f, 153.32f, 183.27f, 108.81f, 121.65f, 216.24f, 168.17f, 172.27f, 219.9f, 151.73f, 146.67f, 195.97f, 134.44f, 122.33f, 144.66f, 180.5f, 151.01f, 187.93f, 108.72f, 139.02f, 146.97f, 191.79f, 126.77f, 131.2f, 133.67f, 102.38f, 118.15f, 176.74f, 155.34f, 170.68f, 101.6f, 165.25f, 125.74f, 155.41f, 151.23f, 180.72f, 202.65f, 121.05f, 169.55f, 164.24f, 95.03f, 217.06f, 174.26f },
        };

        assertThat(solution(stocks, prices)[0], is("SPNS"));
        assertThat(solution(stocks, prices)[1], is("AMOV"));
        assertThat(solution(stocks, prices)[2], is("VRTS"));
    }

    @Test void testSolution_three() {
        String[] stocks = new String[] { "EXPE", "CEVA", "LDRI", "CRSAU", "PEBK", "IMGN", "IFMK", "VIOT", "TBNK", "FDT", "MOMO", "KTOVW", "VONV", "SXTC", "WIRE", "ALGRW", "NYMT", "QYLD", "MBRX", "SMTC", "ALJJ", "PNFP", "TKKSW", "SBFG", "AMRWW", "CRON", "PLXP", "TDAC", "IVENC", "ISCA", "INOV", "ARPO", "TGTX", "CRESY", "MRTX", "HMSY", "TGLS", "RCKY", "BMCH", "VNDA", "MNDO", "CHUY", "DFFN", "CVLT", "ANDA", "CAR", "VCYT", "HWCC", "DCOM", "TSC", "ONCY", "GNMK", "ACAM", "VUZI", "RVNC", "CSSE", "RDHL", "CHI", "EQRR", "USATP", "FLNT", "IPLDP", "FLIR", "COWNZ", "NXTD", "LINDW", "IVFGC", "SGLBW", "SND", "BL", "ALGRR", "ASML", "EVGN", "MSBI", "CFO", "BKNG", "SYNL", "BIIB", "NVIV", "MBOT", "DYSL", "GDEN", "EDAP", "FTA", "STKS", "BKEP", "OTTR", "LORL", "FGEN", "AUTL", "ATSG", "FTR", "ORBC", "PBFS", "JCS", "MBUU", "JACK", "LCNB", "TACO", "EPAY" };
        float[][] prices = new float[][] {
                { 178.58f, 143.69f, 105.91f, 150.25f, 144.13f, 133.01f, 148.69f, 103.95f, 120.23f, 166.36f, 204.8f, 155.67f, 156.32f, 158.28f, 118.99f, 185.56f, 180.55f, 108.99f, 116.3f, 137.3f, 106.82f, 116.83f, 154.4f, 196.97f, 162.96f, 124.09f, 138.73f, 128.24f, 135.42f, 194.47f, 153.15f, 162.13f, 222.77f, 202.79f, 186.25f, 116.74f, 167.11f, 136.13f, 236.63f, 176.11f, 192.52f, 192.11f, 149.87f, 173.19f, 162.98f, 191.65f, 183.4f, 181.08f, 129.42f, 152.67f, 187.44f, 138.15f, 118.49f, 104.03f, 154.3f, 84.8f, 140.85f, 141.28f, 164.23f, 202.44f, 128.93f, 121.66f, 109.44f, 170.44f, 127.27f, 210.75f, 147.53f, 164.68f, 102.08f, 178.78f, 139.62f, 158.4f, 192.79f, 133.24f, 179.9f, 125.43f, 108.37f, 190.08f, 175.51f, 158.08f, 165.11f, 192.92f, 96.96f, 157.14f, 171.64f, 116.57f, 121.09f, 126.46f, 167.79f, 151.98f, 129.7f, 146.99f, 123.89f, 99.49f, 98.57f, 145.68f, 156.14f, 219.92f, 176.02f, 106.59f },
                { 193.96f, 108.26f, 105.28f, 144.58f, 144.29f, 121.7f, 125.56f, 108.3f, 135.39f, 184.53f, 177.47f, 192.53f, 151.11f, 165.46f, 135.53f, 182.64f, 179.7f, 128.37f, 93.81f, 133.26f, 110.66f, 114.04f, 151.31f, 189.06f, 153.78f, 156.57f, 134.39f, 121.06f, 136.79f, 191.37f, 191.98f, 152.22f, 232.38f, 186.46f, 193.87f, 115.91f, 150.17f, 109.12f, 167.75f, 155.57f, 160.12f, 162.27f, 166.16f, 183.61f, 186.21f, 167.8f, 197.43f, 193.85f, 115.77f, 130.21f, 213.24f, 128.74f, 172.51f, 124.15f, 126.03f, 100.19f, 110.26f, 152.76f, 165.12f, 153.02f, 142.28f, 116.04f, 101.19f, 181.59f, 158.82f, 201.7f, 137.28f, 173.92f, 115.05f, 173.18f, 139.54f, 181.03f, 185.26f, 141.19f, 153.95f, 146.05f, 139.68f, 155.8f, 144.24f, 181.48f, 179.61f, 177.89f, 102.08f, 143.13f, 169.33f, 139.08f, 133.44f, 151.31f, 162.32f, 149.93f, 173.5f, 142.2f, 119.16f, 112.03f, 113.18f, 170.89f, 223.96f, 171.14f, 175.11f, 113.03f },
                { 185.98f, 133.53f, 100.05f, 143.49f, 120.5f, 127.32f, 117.19f, 95.4f, 135.27f, 166.17f, 175.83f, 183.0f, 150.67f, 177.98f, 105.99f, 192.41f, 173.4f, 152.32f, 112.82f, 133.57f, 127.6f, 106.14f, 165.04f, 163.39f, 147.44f, 128.66f, 139.06f, 124.17f, 117.74f, 182.66f, 187.7f, 187.74f, 184.12f, 178.0f, 185.98f, 90.77f, 156.02f, 107.42f, 179.22f, 180.0f, 160.93f, 209.89f, 164.42f, 139.98f, 150.15f, 176.54f, 202.48f, 173.53f, 119.95f, 163.93f, 168.79f, 119.7f, 166.31f, 104.41f, 133.21f, 98.65f, 129.08f, 141.4f, 138.45f, 166.13f, 135.96f, 96.74f, 115.39f, 174.18f, 159.77f, 170.07f, 112.35f, 187.12f, 105.95f, 169.62f, 128.97f, 185.3f, 156.56f, 141.45f, 162.59f, 129.26f, 130.75f, 182.82f, 152.9f, 183.89f, 167.26f, 210.15f, 91.12f, 150.7f, 185.8f, 130.29f, 143.92f, 138.35f, 151.02f, 149.47f, 150.31f, 107.38f, 115.01f, 107.78f, 105.72f, 171.93f, 169.82f, 203.76f, 195.26f, 113.44f },
                { 209.82f, 131.98f, 118.64f, 144.25f, 116.97f, 126.42f, 162.53f, 103.8f, 118.8f, 156.83f, 185.55f, 147.71f, 144.15f, 174.35f, 123.21f, 208.13f, 133.71f, 139.72f, 104.47f, 111.37f, 141.0f, 118.04f, 177.34f, 177.47f, 182.1f, 129.38f, 171.62f, 123.84f, 155.71f, 238.28f, 165.31f, 171.35f, 198.26f, 166.41f, 202.64f, 93.43f, 166.84f, 95.0f, 184.33f, 189.88f, 166.72f, 211.72f, 149.72f, 196.32f, 165.19f, 201.79f, 243.92f, 209.96f, 128.35f, 133.47f, 204.34f, 136.02f, 165.22f, 106.17f, 121.18f, 93.66f, 137.64f, 133.67f, 147.7f, 165.38f, 128.32f, 114.99f, 111.4f, 190.17f, 147.72f, 179.84f, 169.79f, 190.51f, 110.93f, 171.83f, 116.91f, 146.41f, 163.06f, 120.62f, 193.33f, 117.11f, 126.5f, 172.72f, 170.18f, 194.67f, 166.77f, 166.97f, 95.09f, 137.79f, 166.78f, 133.62f, 142.92f, 145.1f, 151.12f, 168.27f, 131.87f, 122.94f, 109.28f, 124.29f, 122.24f, 155.0f, 169.63f, 197.19f, 172.92f, 121.91f },
                { 176.32f, 106.64f, 86.96f, 137.17f, 133.6f, 131.88f, 140.46f, 98.98f, 126.19f, 170.04f, 206.39f, 207.1f, 164.22f, 120.09f, 115.69f, 166.4f, 163.47f, 125.88f, 106.56f, 126.92f, 124.95f, 113.13f, 182.04f, 199.93f, 150.59f, 125.31f, 117.11f, 108.43f, 122.21f, 206.03f, 170.77f, 171.07f, 171.33f, 192.0f, 186.36f, 93.72f, 158.38f, 121.26f, 188.09f, 194.62f, 193.15f, 171.97f, 166.13f, 196.67f, 202.3f, 161.76f, 203.59f, 201.0f, 144.79f, 96.25f, 222.69f, 145.91f, 139.65f, 101.1f, 116.33f, 106.54f, 119.44f, 148.39f, 179.9f, 192.86f, 138.59f, 146.8f, 108.14f, 176.27f, 169.01f, 193.54f, 117.49f, 131.54f, 129.46f, 146.01f, 118.27f, 215.28f, 151.44f, 114.38f, 145.75f, 168.62f, 138.66f, 197.25f, 156.4f, 167.79f, 176.4f, 180.38f, 90.62f, 160.57f, 142.02f, 122.62f, 133.46f, 135.78f, 161.97f, 144.43f, 151.97f, 146.97f, 109.72f, 103.91f, 132.54f, 166.88f, 200.41f, 214.94f, 171.48f, 127.55f },
                { 158.39f, 128.13f, 120.79f, 122.1f, 129.62f, 126.69f, 158.99f, 111.31f, 126.21f, 176.95f, 186.53f, 188.57f, 174.04f, 153.02f, 122.82f, 201.23f, 198.67f, 136.18f, 122.22f, 125.44f, 147.6f, 127.0f, 166.71f, 212.49f, 147.51f, 139.29f, 155.03f, 106.57f, 130.32f, 197.0f, 179.85f, 156.75f, 179.8f, 146.78f, 171.51f, 103.87f, 156.05f, 129.26f, 216.84f, 209.48f, 190.37f, 188.75f, 145.96f, 173.47f, 152.76f, 215.66f, 174.84f, 159.58f, 147.07f, 155.49f, 190.89f, 120.0f, 138.07f, 80.83f, 110.78f, 101.11f, 164.84f, 165.86f, 137.55f, 152.01f, 122.52f, 143.51f, 96.42f, 201.2f, 157.24f, 162.63f, 164.81f, 176.97f, 117.13f, 190.46f, 135.66f, 159.12f, 188.23f, 114.5f, 195.23f, 128.63f, 142.43f, 189.38f, 171.32f, 184.99f, 187.02f, 185.95f, 127.9f, 175.65f, 154.14f, 108.96f, 129.2f, 143.59f, 167.88f, 135.94f, 148.94f, 134.43f, 96.73f, 107.26f, 128.96f, 159.23f, 191.03f, 199.14f, 155.97f, 134.26f },
                { 188.37f, 141.2f, 102.7f, 128.53f, 146.36f, 114.62f, 150.62f, 112.54f, 89.76f, 150.5f, 151.32f, 202.67f, 148.84f, 157.13f, 116.01f, 160.7f, 152.8f, 107.89f, 87.03f, 128.27f, 111.29f, 100.24f, 144.94f, 198.81f, 171.52f, 127.57f, 145.47f, 89.15f, 132.35f, 210.14f, 190.21f, 167.89f, 179.13f, 171.38f, 169.3f, 95.16f, 177.96f, 98.69f, 202.99f, 182.69f, 180.74f, 200.4f, 177.09f, 177.47f, 160.18f, 185.67f, 196.61f, 197.34f, 121.1f, 137.15f, 188.74f, 127.01f, 135.92f, 113.26f, 115.34f, 88.31f, 159.3f, 138.87f, 181.59f, 171.53f, 134.76f, 131.14f, 106.48f, 169.28f, 158.99f, 206.83f, 140.5f, 176.38f, 107.49f, 142.91f, 119.57f, 207.71f, 175.26f, 161.56f, 161.09f, 142.2f, 131.97f, 164.46f, 157.44f, 138.75f, 167.66f, 203.46f, 106.94f, 174.08f, 159.38f, 117.68f, 130.91f, 146.48f, 170.78f, 137.09f, 128.94f, 184.16f, 111.26f, 108.47f, 124.61f, 131.48f, 186.68f, 193.99f, 201.43f, 134.96f },
                { 203.48f, 113.86f, 108.78f, 161.43f, 124.81f, 131.65f, 150.27f, 119.8f, 132.05f, 166.7f, 165.35f, 231.31f, 161.94f, 162.37f, 101.79f, 192.03f, 156.59f, 122.88f, 90.8f, 138.82f, 116.7f, 112.92f, 178.36f, 204.65f, 146.4f, 163.49f, 159.06f, 128.33f, 119.95f, 187.25f, 203.79f, 153.31f, 181.51f, 162.84f, 196.57f, 120.41f, 173.05f, 112.96f, 180.26f, 198.04f, 202.31f, 218.9f, 171.23f, 198.24f, 151.19f, 201.42f, 185.3f, 194.23f, 147.88f, 161.71f, 178.74f, 145.13f, 143.32f, 98.24f, 118.56f, 97.29f, 115.7f, 172.48f, 168.57f, 184.38f, 126.23f, 127.22f, 118.26f, 205.37f, 154.44f, 184.53f, 152.36f, 173.42f, 120.6f, 174.02f, 139.59f, 143.52f, 139.88f, 151.24f, 207.17f, 149.23f, 101.52f, 191.9f, 162.84f, 171.08f, 177.59f, 181.01f, 122.52f, 154.94f, 158.48f, 121.58f, 137.17f, 147.58f, 148.13f, 146.29f, 138.74f, 155.63f, 95.99f, 116.62f, 120.64f, 208.19f, 217.1f, 189.11f, 150.18f, 97.82f },
                { 161.06f, 141.06f, 80.37f, 158.58f, 126.53f, 117.22f, 139.92f, 119.85f, 129.39f, 168.51f, 160.29f, 198.2f, 172.81f, 179.69f, 104.28f, 173.96f, 186.66f, 147.39f, 90.91f, 138.69f, 123.38f, 104.82f, 140.02f, 228.6f, 155.39f, 154.3f, 146.35f, 99.82f, 145.54f, 207.81f, 164.1f, 173.36f, 176.59f, 163.28f, 182.98f, 102.63f, 174.12f, 114.85f, 198.07f, 184.59f, 206.63f, 240.24f, 171.83f, 151.27f, 147.45f, 187.55f, 190.67f, 176.99f, 144.33f, 105.15f, 228.32f, 134.8f, 131.8f, 102.32f, 122.25f, 98.43f, 140.04f, 138.24f, 124.22f, 162.71f, 136.57f, 110.54f, 101.18f, 162.45f, 168.45f, 206.74f, 171.81f, 203.9f, 134.23f, 162.98f, 150.17f, 174.41f, 169.08f, 122.24f, 180.02f, 148.92f, 118.02f, 209.72f, 176.37f, 145.0f, 176.8f, 178.26f, 116.02f, 192.04f, 180.94f, 133.81f, 168.25f, 120.11f, 162.62f, 123.15f, 130.53f, 140.07f, 109.05f, 113.59f, 118.2f, 142.3f, 174.82f, 205.14f, 212.44f, 124.0f },
                { 177.76f, 117.74f, 118.27f, 120.46f, 141.37f, 145.31f, 128.92f, 94.17f, 149.69f, 154.05f, 203.56f, 189.87f, 109.95f, 161.54f, 112.39f, 177.53f, 191.63f, 135.01f, 96.63f, 140.27f, 125.37f, 90.17f, 144.59f, 183.22f, 141.61f, 144.1f, 160.71f, 96.07f, 140.1f, 181.79f, 175.15f, 174.64f, 185.24f, 157.43f, 195.33f, 92.39f, 173.65f, 120.43f, 198.57f, 200.2f, 177.63f, 181.36f, 151.27f, 159.89f, 162.23f, 214.01f, 202.52f, 154.64f, 127.16f, 145.82f, 187.58f, 133.22f, 152.5f, 133.91f, 109.78f, 97.66f, 133.55f, 146.69f, 176.27f, 152.21f, 81.9f, 126.34f, 100.67f, 158.7f, 116.98f, 205.8f, 174.9f, 188.37f, 125.97f, 129.37f, 131.14f, 181.31f, 174.71f, 130.05f, 217.69f, 154.35f, 113.93f, 240.7f, 136.5f, 197.2f, 173.11f, 173.54f, 114.7f, 185.95f, 159.17f, 117.41f, 136.0f, 154.01f, 152.32f, 128.42f, 145.62f, 142.73f, 105.79f, 123.26f, 112.45f, 161.36f, 214.1f, 199.49f, 206.17f, 125.78f },
                { 182.96f, 135.23f, 99.1f, 143.59f, 110.5f, 117.13f, 134.77f, 112.86f, 128.91f, 162.66f, 132.32f, 176.5f, 164.38f, 200.32f, 135.73f, 141.0f, 167.41f, 141.0f, 102.23f, 127.27f, 135.25f, 112.81f, 151.77f, 156.71f, 132.39f, 120.03f, 161.75f, 105.45f, 121.01f, 205.43f, 168.23f, 186.95f, 197.38f, 180.04f, 134.79f, 105.04f, 145.55f, 118.92f, 196.12f, 193.96f, 218.28f, 203.45f, 167.19f, 166.84f, 180.07f, 220.53f, 177.08f, 181.25f, 125.9f, 147.88f, 162.52f, 115.96f, 142.6f, 96.47f, 118.9f, 103.65f, 148.84f, 162.85f, 157.86f, 196.01f, 135.23f, 119.16f, 115.86f, 183.41f, 166.43f, 203.23f, 164.13f, 191.64f, 128.31f, 172.68f, 137.42f, 163.39f, 164.08f, 133.18f, 172.71f, 129.9f, 124.55f, 175.56f, 155.47f, 153.15f, 169.79f, 192.09f, 91.74f, 155.62f, 181.97f, 119.27f, 123.29f, 154.13f, 166.88f, 128.45f, 157.5f, 129.24f, 110.42f, 100.85f, 124.2f, 160.19f, 196.0f, 163.58f, 210.09f, 102.23f },
                { 220.87f, 126.31f, 104.18f, 149.49f, 89.86f, 139.7f, 152.87f, 104.85f, 114.26f, 159.43f, 179.93f, 207.22f, 159.55f, 174.63f, 116.94f, 199.83f, 209.79f, 120.96f, 109.06f, 132.52f, 117.28f, 109.02f, 134.38f, 202.7f, 172.14f, 135.9f, 143.6f, 118.76f, 126.53f, 178.26f, 193.09f, 155.1f, 191.53f, 190.07f, 176.91f, 120.6f, 152.78f, 130.47f, 226.25f, 210.03f, 176.06f, 178.59f, 169.6f, 164.28f, 150.53f, 198.52f, 165.93f, 169.45f, 128.75f, 150.85f, 170.62f, 156.19f, 161.86f, 90.44f, 131.7f, 113.74f, 134.94f, 151.03f, 154.37f, 178.99f, 119.05f, 116.08f, 102.63f, 171.07f, 162.0f, 168.06f, 150.01f, 176.43f, 124.67f, 151.14f, 154.65f, 147.46f, 209.0f, 128.69f, 181.54f, 152.88f, 125.52f, 200.09f, 158.45f, 167.25f, 171.94f, 153.72f, 94.04f, 185.44f, 166.71f, 133.55f, 126.75f, 142.85f, 177.8f, 158.82f, 140.79f, 140.37f, 101.39f, 88.95f, 128.18f, 162.19f, 183.31f, 239.33f, 180.79f, 107.51f },
                { 178.39f, 87.57f, 98.4f, 131.25f, 113.21f, 130.22f, 125.35f, 102.24f, 129.06f, 153.2f, 186.27f, 172.78f, 122.11f, 150.49f, 109.68f, 182.64f, 195.96f, 119.52f, 125.27f, 137.02f, 134.49f, 130.36f, 146.4f, 206.99f, 149.53f, 136.81f, 144.79f, 122.42f, 130.87f, 193.0f, 168.2f, 184.82f, 179.22f, 149.42f, 220.7f, 86.22f, 145.66f, 118.5f, 204.84f, 202.65f, 170.73f, 216.06f, 164.56f, 174.47f, 181.4f, 212.21f, 201.38f, 187.35f, 134.15f, 159.37f, 166.02f, 131.68f, 147.84f, 128.45f, 130.72f, 117.55f, 162.47f, 123.18f, 129.52f, 200.32f, 153.16f, 122.08f, 112.55f, 172.24f, 146.06f, 216.63f, 153.26f, 147.75f, 115.88f, 164.68f, 134.36f, 180.29f, 161.04f, 140.37f, 186.75f, 130.86f, 137.52f, 206.18f, 166.55f, 180.06f, 161.38f, 217.4f, 114.82f, 133.22f, 142.05f, 124.25f, 112.03f, 139.48f, 158.92f, 144.23f, 110.35f, 133.74f, 105.52f, 108.54f, 145.73f, 164.62f, 176.12f, 204.88f, 230.53f, 107.71f },
                { 218.52f, 145.68f, 94.4f, 150.72f, 144.87f, 117.7f, 120.16f, 131.29f, 152.72f, 169.11f, 172.55f, 190.91f, 169.02f, 166.19f, 134.55f, 185.0f, 173.09f, 131.21f, 102.04f, 121.23f, 123.98f, 114.71f, 134.93f, 222.58f, 155.2f, 163.35f, 172.45f, 111.42f, 125.15f, 218.15f, 196.79f, 180.56f, 197.1f, 162.42f, 162.54f, 108.41f, 126.7f, 117.59f, 173.23f, 207.93f, 173.44f, 210.93f, 170.13f, 147.31f, 190.59f, 157.18f, 202.34f, 219.88f, 120.87f, 157.33f, 181.02f, 130.49f, 161.69f, 107.61f, 171.29f, 98.04f, 143.85f, 145.37f, 164.28f, 199.47f, 121.52f, 120.09f, 96.66f, 197.16f, 128.18f, 176.89f, 148.15f, 187.96f, 113.43f, 178.39f, 125.12f, 190.62f, 168.34f, 132.11f, 150.39f, 131.93f, 111.95f, 145.87f, 142.42f, 144.91f, 162.85f, 218.52f, 122.47f, 143.01f, 187.12f, 129.89f, 145.61f, 153.85f, 171.82f, 127.7f, 160.26f, 129.62f, 101.81f, 109.17f, 142.08f, 164.83f, 183.63f, 190.18f, 198.26f, 121.6f },
                { 181.18f, 158.98f, 112.56f, 159.53f, 142.22f, 138.92f, 164.75f, 100.81f, 127.61f, 142.7f, 198.04f, 186.27f, 144.2f, 162.35f, 113.9f, 184.99f, 162.65f, 146.22f, 118.71f, 128.79f, 107.25f, 96.01f, 170.85f, 173.77f, 140.87f, 159.07f, 174.7f, 104.76f, 123.38f, 168.01f, 191.3f, 199.16f, 167.64f, 151.45f, 166.15f, 102.12f, 150.27f, 121.85f, 204.03f, 189.66f, 199.97f, 211.48f, 184.99f, 155.9f, 164.83f, 171.7f, 187.71f, 156.19f, 132.47f, 150.99f, 218.53f, 148.32f, 152.04f, 109.56f, 120.13f, 117.26f, 166.03f, 163.25f, 169.79f, 172.62f, 129.74f, 120.33f, 115.33f, 176.78f, 142.43f, 206.66f, 150.48f, 172.68f, 122.97f, 165.98f, 110.0f, 178.95f, 179.18f, 124.94f, 166.13f, 159.34f, 139.44f, 182.23f, 134.39f, 215.61f, 162.43f, 157.39f, 108.3f, 139.38f, 169.9f, 128.09f, 145.92f, 149.44f, 179.43f, 154.76f, 136.93f, 157.5f, 100.11f, 88.27f, 112.7f, 164.92f, 175.32f, 180.99f, 198.53f, 104.84f },
                { 188.78f, 135.01f, 90.21f, 120.33f, 105.15f, 131.98f, 153.13f, 112.45f, 142.91f, 169.66f, 187.44f, 176.11f, 134.12f, 179.44f, 108.37f, 225.3f, 132.13f, 133.29f, 93.28f, 105.8f, 113.16f, 83.31f, 154.11f, 202.13f, 147.8f, 146.65f, 154.31f, 110.18f, 87.48f, 190.1f, 157.37f, 153.69f, 145.81f, 143.79f, 207.86f, 108.96f, 173.26f, 112.91f, 214.35f, 200.28f, 196.15f, 220.41f, 182.18f, 211.58f, 170.78f, 182.6f, 181.9f, 178.83f, 140.57f, 161.32f, 183.71f, 134.74f, 104.43f, 118.31f, 100.14f, 93.82f, 124.9f, 152.79f, 199.33f, 172.83f, 122.83f, 149.7f, 127.71f, 167.29f, 139.58f, 172.22f, 166.58f, 162.11f, 108.37f, 173.76f, 121.05f, 188.0f, 188.19f, 116.32f, 202.13f, 147.05f, 146.18f, 199.47f, 132.5f, 178.22f, 169.09f, 153.96f, 96.72f, 151.83f, 139.39f, 151.27f, 152.34f, 159.45f, 176.88f, 136.13f, 136.64f, 145.15f, 104.37f, 127.21f, 110.9f, 163.72f, 204.06f, 195.98f, 186.05f, 119.1f },
                { 185.91f, 142.06f, 112.63f, 120.57f, 136.78f, 118.39f, 127.25f, 118.78f, 124.89f, 179.71f, 207.24f, 176.13f, 144.09f, 173.92f, 134.53f, 215.49f, 181.15f, 130.72f, 87.89f, 113.92f, 121.5f, 102.77f, 126.79f, 165.08f, 156.48f, 152.8f, 167.44f, 94.97f, 124.22f, 178.11f, 139.12f, 189.08f, 208.61f, 173.94f, 198.37f, 97.63f, 129.11f, 120.72f, 147.5f, 203.16f, 179.04f, 199.69f, 169.03f, 183.27f, 182.16f, 199.45f, 207.43f, 188.8f, 128.1f, 149.05f, 193.39f, 132.0f, 122.44f, 96.21f, 118.55f, 98.23f, 110.89f, 151.43f, 171.37f, 207.17f, 114.05f, 126.23f, 112.79f, 159.22f, 132.04f, 160.21f, 131.3f, 208.62f, 128.33f, 149.97f, 169.11f, 169.12f, 145.66f, 120.36f, 171.84f, 132.21f, 106.51f, 188.6f, 155.87f, 181.32f, 184.78f, 171.22f, 100.41f, 139.24f, 138.65f, 102.24f, 148.03f, 120.15f, 141.94f, 134.61f, 182.1f, 155.78f, 121.19f, 97.7f, 104.09f, 152.83f, 197.54f, 209.65f, 175.47f, 115.19f },
                { 194.96f, 126.93f, 101.44f, 139.35f, 121.43f, 113.08f, 148.6f, 103.78f, 131.63f, 163.12f, 176.37f, 203.61f, 157.8f, 141.53f, 122.27f, 135.67f, 186.43f, 114.51f, 102.53f, 140.04f, 128.13f, 118.08f, 139.54f, 157.67f, 154.18f, 100.64f, 119.15f, 119.71f, 122.19f, 197.41f, 168.57f, 185.65f, 207.0f, 173.7f, 176.89f, 101.77f, 149.74f, 112.05f, 192.35f, 197.32f, 184.51f, 158.36f, 176.25f, 176.66f, 155.54f, 167.26f, 170.92f, 185.63f, 125.91f, 155.77f, 222.63f, 149.7f, 166.24f, 120.74f, 133.28f, 105.01f, 159.44f, 150.21f, 131.47f, 175.22f, 145.63f, 96.85f, 99.79f, 168.67f, 165.55f, 162.26f, 106.11f, 164.55f, 148.37f, 168.06f, 131.31f, 163.3f, 181.53f, 123.58f, 193.92f, 148.41f, 123.73f, 196.61f, 136.34f, 160.16f, 152.6f, 168.27f, 97.81f, 173.29f, 157.96f, 135.76f, 150.81f, 137.34f, 172.9f, 119.86f, 132.95f, 158.59f, 108.9f, 126.09f, 112.44f, 173.83f, 192.89f, 218.39f, 212.32f, 99.05f },
                { 205.8f, 137.58f, 95.35f, 160.89f, 144.46f, 102.61f, 139.91f, 109.76f, 126.2f, 159.01f, 188.68f, 214.52f, 172.81f, 147.92f, 116.47f, 167.97f, 167.96f, 130.88f, 126.38f, 111.58f, 110.57f, 107.89f, 149.81f, 196.89f, 149.39f, 143.45f, 164.71f, 96.82f, 129.85f, 163.15f, 152.15f, 176.54f, 188.59f, 175.82f, 186.31f, 100.49f, 129.07f, 111.26f, 190.49f, 205.02f, 152.51f, 180.58f, 146.91f, 163.98f, 158.0f, 202.81f, 182.65f, 186.51f, 106.65f, 136.61f, 159.61f, 132.22f, 142.07f, 103.8f, 144.94f, 91.36f, 142.27f, 161.17f, 164.17f, 200.88f, 148.7f, 130.38f, 103.23f, 155.9f, 129.5f, 180.96f, 182.08f, 180.12f, 119.52f, 165.79f, 119.17f, 170.31f, 177.5f, 131.42f, 186.48f, 139.21f, 106.05f, 178.25f, 147.29f, 173.1f, 178.15f, 162.46f, 106.46f, 196.08f, 163.04f, 136.04f, 145.44f, 149.92f, 174.68f, 151.27f, 151.61f, 152.2f, 103.65f, 105.33f, 123.14f, 181.3f, 184.71f, 206.65f, 204.33f, 149.55f },
                { 184.3f, 151.54f, 116.77f, 181.49f, 102.16f, 144.16f, 137.87f, 105.37f, 120.45f, 172.23f, 226.38f, 193.06f, 150.11f, 146.76f, 120.36f, 182.9f, 191.41f, 147.37f, 113.9f, 155.19f, 111.34f, 103.33f, 142.79f, 195.69f, 141.69f, 174.6f, 141.9f, 116.7f, 118.3f, 196.1f, 173.34f, 189.69f, 171.8f, 145.24f, 197.88f, 100.6f, 161.28f, 110.63f, 220.94f, 179.01f, 191.33f, 181.47f, 147.26f, 174.87f, 165.44f, 170.55f, 190.29f, 163.97f, 137.37f, 124.32f, 209.66f, 131.81f, 155.04f, 99.56f, 147.47f, 111.32f, 152.66f, 140.7f, 152.8f, 216.02f, 133.63f, 128.18f, 97.63f, 188.65f, 153.47f, 198.25f, 113.78f, 194.82f, 76.74f, 179.39f, 116.7f, 179.53f, 159.4f, 121.4f, 179.45f, 131.9f, 132.87f, 196.65f, 127.08f, 190.98f, 167.32f, 190.22f, 106.43f, 144.25f, 167.81f, 131.65f, 150.51f, 112.33f, 168.22f, 164.93f, 139.27f, 135.84f, 101.22f, 112.65f, 121.63f, 177.7f, 189.88f, 205.52f, 181.93f, 110.19f }
        };

        assertThat(solution(stocks, prices)[0], is("LCNB"));
        assertThat(solution(stocks, prices)[1], is("CHUY"));
        assertThat(solution(stocks, prices)[2], is("BMCH"));
    }

}
