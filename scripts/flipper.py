import cv2
import argparse

ap = argparse.ArgumentParser()
ap.add_argument("image", help="Path to the image")
args = ap.parse_args()

cv2.imwrite(args.image, cv2.flip(cv2.imread(ap.parse_args().image), 0))