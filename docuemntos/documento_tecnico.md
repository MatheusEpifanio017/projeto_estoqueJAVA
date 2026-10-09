# Clinic Analytics Database

## Overview

This PostgreSQL database supports a fictional clinic analytics project. All records are fictional and anonymized. The database stores patients, professionals, specialties, insurance plans, consultation rooms and appointment records.

The `consulta` table contains the operational records used in the analysis. Each record represents one scheduled appointment.

## Main tables

| Table | Description |
|---|---|
| `paciente` | Stores patient registration data and the selected insurance plan. |
| `profissional` | Stores healthcare professional data and the related specialty. |
| `especialidade` | Stores clinic specialties. |
| `convenio` | Stores insurance plans and payment categories. |
| `consultorio` | Stores consultation room information. |
| `consulta` | Stores appointment and attendance information. |

## Appointment records

An appointment record links one patient, one professional and one consultation room. The `data_agendamento` field stores when the appointment was scheduled. The `data_hora_consulta` field stores the planned appointment date and time.

The `status` field indicates the current appointment situation:

| Status | Meaning |
|---|---|
| `AGENDADA` | The appointment is scheduled but not completed yet. |
| `REALIZADA` | The appointment was completed. |
| `FALTOU` | The patient did not attend the appointment. |
| `CANCELADA` | The appointment was cancelled. |

The `tipo_atendimento` field accepts `PRESENCIAL` for an in-person appointment and `TELECONSULTA` for a remote appointment.

## Data rules

1. Every appointment must reference one patient, one professional and one consultation room.
2. The scheduling date and time cannot be later than the planned appointment date and time.
3. The actual start and end times are optional. When one is available, the other one must also be available.
4. The actual end time cannot be earlier than the actual start time.
5. The charged amount may be empty when an appointment was not completed.
6. A patient is linked to one insurance plan in the current registration data.
7. A professional is linked to one specialty.

## Analysis notes

Use `status = 'REALIZADA'` when the question is about completed appointments. Use `status = 'FALTOU'` when the question is about no-show rate. Before calculating attendance duration, verify that `inicio_atendimento` and `fim_atendimento` are not empty.

## Useful terms

- `table`: a collection of related records.
- `field`: one data attribute in a table.
- `primary key`: a unique identifier for a record.
- `foreign key`: a field that references a record in another table.
- `required`: a value that cannot be empty.
- `nullable`: a value that may be empty.
- `constraint`: a rule enforced by the database.