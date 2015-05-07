
public class GoogleInterview {
	
	public static void main(String[] args)
	{		
//		int[][] matrix = { {1, 2, 3},
//						   {4, 5, 6},
//						   {7, 8, 9}
//						 };
		
//		int[][] matrix = { {1, 2, 3, 4},
//				   {5, 6, 7, 8},
//				   {9, 10, 11, 12},
//				   {13, 14, 15, 16}
//				 };
		
		int[][] matrix = { {5, 7},
						   {8, 9}
				 };
		
		System.out.println("before");
		printMatrix(matrix);
		System.out.println("after");
		rotateSquareMatrix(matrix);
		printMatrix(matrix);
	}
	
	private static void printMatrix(int[][] matrix)
	{
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	/*
    Write code to rotate a square matrix: 
    Input: 
    1 2 3 
    4 5 6 
    7 8 9 
    
    Output: 
    4 1 2 
    7 5 3 
    8 9 6
*/

public static void rotateSquareMatrix(int[][]  matrix)
{
    /*
        1. iterate through each layer.
           num of layers == row.length / 2;
            - for each element in layer
                
                rotate clockwise
    */
    
    int layers = matrix.length / 2;
    
    for (int i = 0; i < layers; i++)
    {
        int layerLength = matrix.length - (i*2);
        int topLeft = matrix[i][i];
        
        // left col
        matrix[i][i] = matrix[i+1][i];
        for (int j = i+2; j < layerLength; j++)
        {
            matrix[i+j-1][i] = matrix[i+j][i];
        }
        
        // bottom row
        matrix[layerLength-1][i] = matrix[layerLength-1][i+1];
        for (int j = i+2; j < layerLength; j++)
        {
            matrix[layerLength-1][j-1] = matrix[layerLength-1][j];
        }
        
        // top right
        matrix[layerLength-1][layerLength-1] = matrix[layerLength-2][layerLength-1];
        for (int j = layerLength - 3; j >= 0; j--)
        {
        	matrix[j+1][layerLength-1] = matrix[j][layerLength-1];
        }
        
        // top row
        for (int j = i+1; j < layerLength - 1; j++)
        {
        	matrix[i][j+1] = matrix[i][j]; 
        }
        matrix[i][i+1] = topLeft;
        
        // top row
//        for (int j = 0; j < matrix.length - (i*2); j++)
//        {
//            int temp = matrix[i][j];
//        }
    }
}

}
