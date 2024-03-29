package br.eti.emersondantas.coursesocialnetwork.api.discipline.services;

import br.eti.emersondantas.coursesocialnetwork.api.discipline.Discipline;
import br.eti.emersondantas.coursesocialnetwork.api.discipline.DisciplineRepository;
import br.eti.emersondantas.coursesocialnetwork.api.discipline.exceptions.DisciplineNotFoundException;
import br.eti.emersondantas.coursesocialnetwork.api.discipline.exceptions.InvalidTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateNoteDisciplineServiceImpl implements UpdateNoteDisciplineService{

    private final DisciplineRepository disciplineRepository;


    @Override
    public Discipline updateNote(Long id, String newNote) {
        Double note;
        try {
            note = Double.parseDouble(newNote);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException();
        }
        Discipline discipline = this.disciplineRepository.findById(id).orElseThrow(DisciplineNotFoundException::new);
        Double lastNote = discipline.getNota() != null ? discipline.getNota() : 0.0;
        discipline.setNota((lastNote + note) / 2);
        return this.disciplineRepository.save(discipline);
    }
}
