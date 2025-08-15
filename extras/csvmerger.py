'''
Created on 12 ago. 2025

@author: sndc3
'''
import csv
from datetime import datetime

# Input CSV files
main_csv = "Activities.csv"        # StudentID;CourseID;ActivityKind;Grade;Date;GenerationKind
credits_csv = "Courses.csv"  # ID;Name;Credits
output_csv = "merged.csv"

# Step 1: Load credits into a dictionary
credits_map = {}
with open(credits_csv, mode="r", encoding="utf-8") as f:
    reader = csv.DictReader(f, delimiter=";")
    for row in reader:
        credits_map[row["ID"]] = row["Credits"]

# Step 2: Process main CSV and transform data
with open(main_csv, mode="r", encoding="utf-8") as f_in, \
     open(output_csv, mode="w", encoding="utf-8", newline="") as f_out:

    reader = csv.DictReader(f_in, delimiter=";")

    fieldnames = [
        "ID", "Activity", "Timestamp", "Career", "Plan",
        "Curricular Unit", "Course Edition", "Course Year",
        "Grade", "Credits"
    ]
    writer = csv.DictWriter(f_out, fieldnames=fieldnames)
    writer.writeheader()

    # Map ActivityKind to Activity
    activity_map = {
        "C": "Evaluation - Course",
        "E": "Evaluation - Exam",
        "I": "Inscription to Course"
    }

    for row in reader:
        activity_kind = row["ActivityKind"]
        if activity_kind not in activity_map:
            continue  # Ignore other types

        # Convert date
        date_str = row["Date"]
        date_obj = datetime.strptime(date_str, "%Y%m%d")
        formatted_date = date_obj.strftime("%a %b %d %H:%M:%S UTC %Y")

        # Determine Course Edition
        course_edition = 1 if date_obj.month <= 7 else 2

        # Get credits from second CSV
        course_id = row["CourseID"]
        credits = credits_map.get(course_id, "")

        writer.writerow({
            "ID": row["StudentID"],
            "Activity": activity_map[activity_kind],
            "Timestamp": formatted_date,
            "Career": "INGENIERIA EN COMPUTACION",
            "Plan": "1997",
            "Curricular Unit": course_id,
            "Course Edition": course_edition,
            "Course Year": date_obj.year,
            "Grade": row["Grade"],
            "Credits": credits
        })

print(f"✅ Merged CSV saved as {output_csv}")