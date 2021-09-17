/**
 * @author Wade - wrshafer3
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */

import java.util.List;
import java.util.Scanner;


import controller.VideoGameHelper;

import model.VideoGameItems;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static VideoGameHelper vgh = new VideoGameHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter video game name: ");
			String videoGame = in.nextLine();
			System.out.print("Enter the year that video games was made: ");
			int creationYear = in.nextInt();
			VideoGameItems toAdd = new VideoGameItems(videoGame, creationYear);
			vgh.insertItem(toAdd);

		}

		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the video game title to delete: ");
			String videoGame = in.nextLine();
			System.out.print("Enter the year of the video game you want to delete: ");
			int creationYear = in.nextInt();
			VideoGameItems toDelete = new VideoGameItems(videoGame, creationYear);
			vgh.deleteItem(toDelete);
		}

		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Game Title");
			System.out.println("2 : Search by Creation Year");
			int searchBy = in.nextInt();
			in.nextLine();
			List<VideoGameItems> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the game name: ");
				String gameName = in.nextLine();
				foundItems = vgh.searchForItemByStore(gameName);
			} else {
				System.out.print("Enter the year the game was made: ");
				int creationYear = in.nextInt();
				foundItems = vgh.searchForItemByItemInt(creationYear);

			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (VideoGameItems l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID do you want to edit?: ");
				int idToEdit = in.nextInt();

				VideoGameItems toEdit = vgh.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getGameName() + " from " + toEdit.getCreationYear());
				System.out.println("1 : Update Game Name");
				System.out.println("2 : Update Year");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Game Name: ");
					String newGameName = in.nextLine();
					toEdit.setGameName(newGameName);
				} else if (update == 2) {
					System.out.print("New Creation Year: ");
					int newCreationYear = in.nextInt();
					toEdit.setCreationYear(newCreationYear);
				}

				vgh.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- This is our program that allows you to enter video game titles and the year they were released! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an video game to the list");
				System.out.println("*  2 -- Edit a video game already on the list");
				System.out.println("*  3 -- Delete an video game from the list");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Close the program");
				System.out.print("*  Your selection shall be? ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					vgh.cleanUp();
					System.out.println("   Until next time!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<VideoGameItems> allItems = vgh.showAllItems();
			for(VideoGameItems singleItem : allItems){
				System.out.println(singleItem.returnItemDetails());
		}

	}
}