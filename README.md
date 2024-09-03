# Erasmus+ Management System

EPLUS este un sistemul conceput pentru a gestiona aplicațiile pentru proiectele Erasmus+. Sistemul se ocupă de mai multe entități cheie: Organizator, Participant, Aplicație și Proiect. Acesta oferă funcționalități pentru gestionarea acestor entități, respectiv efectuarea diferitelor operațiuni și interogări legate de ele.

## Entități

1. Organization - reprezintă organizatorul proiectelor Erasmus+.
2. Participant - reprezintă un participant care aplică pentru proiecte Erasmus+.
3. Project - reprezintă un proiect Erasmus+, ce aduce împreună organizațiile cu participanții
4. Application - reprezintă o aplicație trimisă de un participant pentru un proiect.

<br>

## Acțiuni / Interogări 

- Adăugarea, actualizare, ștergerea, afișare detaliilor unui organizator.
- Adăugarea, actualizarea, ștergerea, afișare detaliilor unui proiect.
- Adăugarea, actualizarea, ștergerea, afișare detaliilor unui participant.
- Adăugarea, actualizarea, ștergerea, afișare detaliilor unei aplicații pentru un proiect
- Listarea tuturor proiectelor
- Listarea tuturor participanților.
- Listarea tuturor organizatorilor.
- Aprobarea sau respingerea unei aplicații.
- Listarea tuturor aplicațiilor pentru un anumit proiect.
- Listarea tuturor aplicațiilor ale unui anumit participant.
- Listatea proiectelor în funcție de țară

<br>

## Object Types

- Organization
- Participant
- Application
- Project
  - YouthExchange
  - TrainingCourse
- ApplicationStatus (enum SUBMITTED, APPROVED, REJECTED)
- ProjectStatus (enum PENDING, ONGOING, FINISHED)
- Location (clasă pentru detaliile privind locația unui anumit proiect)
- ContactInfo (Clasă suplimentară pentru stocarea informațiilor de contact)