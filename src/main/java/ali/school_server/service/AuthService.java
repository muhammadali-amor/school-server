package ali.school_server.service;

import ali.school_server.entity.User;
import ali.school_server.payload.*;
import ali.school_server.repository.AuthRepository;
import ali.school_server.repository.RoleRepository;
import ali.school_server.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
//    private final MessageRepository messageRepository;


//    private final JavaMailSender mailSender;


    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return (UserDetails) authRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("getUser"));
    }

    public UserDetails getUserById(UUID id) {
        return (UserDetails) authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    public HttpEntity<?> login(LoginDto request, AuthenticationManager authenticationManager) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword())
        );
        User user = authRepository.findUserByPhoneNumber(request.getPhoneNumber()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        ResToken resToken = new ResToken(generateToken(request.getPhoneNumber()));
        System.out.println(ResponseEntity.ok(getMal(user, resToken)));
        return ResponseEntity.ok(getMal(user, resToken));
    }

//    public ApiResponse<?> resetPassword(ResetPasswordDto resetPasswordDto) {
//        try {
//            User user = authRepository.findUserByEmail(resetPasswordDto.getEmail()).orElseThrow(() -> new Project.Restaurantbackend.exception.ResourceNotFoundException(404, "get user", "email error", resetPasswordDto.getEmail()));
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("assd16657@gmail.com");
//            message.setTo(resetPasswordDto.getEmail());
//            message.setSubject("Restaurant akkauntga kirish Hafsizlik bildirishnomasi!!!");
//            message.setText("Resturant akkauntga kirishga urinish \n     telefon raqam: " + user.getPhoneNumber() + "\n     parol:         " + user.getResetPassword() + "\nBu siz emasmi? Ushbu login parolni hech kimga bermang!!!");
//            mailSender.send(message);
//            return new ApiResponse<>("Login parol emailga yuborildi", true);
//        } catch (Exception e) {
//            return new ApiResponse<>("Reset passwordda xatolik", false);
//        }
//    }

    public GetData getMal(User user, ResToken resToken) {
        return new GetData(user, resToken);
    }

    private String generateToken(String phoneNumber) {
        User user = authRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("getUser"));
        return jwtTokenProvider.generateAccessToken(user);
    }


    public HttpEntity<?> addUser(RegisterDto registerDto, AuthenticationManager authenticationManager) {
        try {
            boolean existsUserByPhoneNumber = authRepository.existsUserByPhoneNumber(registerDto.getPhoneNumber());
            boolean userByEmail = authRepository.existsUserByEmail(registerDto.getEmail());
            if (!existsUserByPhoneNumber) {
                if (!userByEmail) {
                    User user = User.builder()
                            .name(registerDto.getName())
                            .surname(registerDto.getSurname())
                            .email(registerDto.getEmail())
                            .phoneNumber(registerDto.getPhoneNumber())
                            .password(passwordEncoder().encode(registerDto.getPassword()))
                            .roles(Collections.singleton(roleRepository.findById(2).orElseThrow(() -> new ResourceNotFoundException("getRole"))))
                            .photoId(registerDto.getPhotoId())
                            .accountNonExpired(true)
                            .accountNonLocked(true)
                            .credentialsNonExpired(true)
                            .enabled(true)
                            .build();
                    User save = authRepository.save(user);
                    LoginDto loginDto = LoginDto.builder()
                            .phoneNumber(save.getPhoneNumber())
                            .password(registerDto.getPassword())
                            .build();
                    return login(loginDto, authenticationManager);
                }
                return ResponseEntity.ok(new ApiResponse<>("Afsuski bunday emaildan foydalanilgan😔", false));
            } else {
                return ResponseEntity.ok(new ApiResponse<>("Afsuski bunday telefon raqamdan avval foydalanilgan😞", false));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>("Xatolik", false));
        }
    }
//    public List<RegisterDto> getUser() {
//        List<User> all = authRepository.findAll();
//        List<RegisterDto> registerDtoList = new ArrayList<>();
//        for (User user : all) {
//            registerDtoList.add(
//                    RegisterDto.builder()
//                            .id(user.getId())
//                            .roles(user.getRoles())
//                            .name(user.getName())
//                            .surname(user.getSurname())
//                            .phoneNumber(user.getPhoneNumber())
//                            .email(user.getEmail())
//                            .password(user.getPassword())
//                            .photoId(user.getPhotoId())
//                            .build()
//            );
//        }
//        return registerDtoList;
//    }

    public ApiResponse<?> deleteUser(UUID id) {
        try {
            User getUser = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
            authRepository.delete(getUser);
            return new ApiResponse<>("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse<>("Xatolik", false);
        }
    }

    public ApiResponse<?> editUser(UUID id, RegisterDto registerDto) {
        try {
            User getUser = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
            switch (registerDto.getMalumot()) {
                case "name" -> {
                    getUser.setName(registerDto.getName());
                    authRepository.save(getUser);
                    return new ApiResponse<>("ism taxrirlandi", true);
                }
                case "surname" -> {
                    getUser.setSurname(registerDto.getSurname());
                    authRepository.save(getUser);
                    return new ApiResponse<>("familiya taxrirlandi", true);
                }
                case "email" -> {
                    getUser.setEmail(registerDto.getEmail());
                    authRepository.save(getUser);
                    return new ApiResponse<>("email taxrirlandi", true);
                }
                case "phoneNumber" -> {
                    getUser.setPhoneNumber(registerDto.getPhoneNumber());
                    authRepository.save(getUser);
                    return new ApiResponse<>("tel raqam taxrirlandi", true);
                }
                case "password" -> {
                    getUser.setPassword(passwordEncoder().encode(registerDto.getPassword()));
//                    getUser.setResetPassword(registerDto.getPassword());
                    authRepository.save(getUser);
                    return new ApiResponse<>("parol taxrirlandi", true);
                }
            }
            return new ApiResponse<>("Taxrirlandi", true);
        } catch (Exception e) {
            return new ApiResponse<>("Xatolik", false);
        }
    }

//    public ApiResponse<?> sendMessage(UUID id, MessageDto messageDto) {
//        try {
//            User sendUser = authRepository.findById(id).orElseThrow(() -> new Project.Restaurantbackend.exception.ResourceNotFoundException(404, "get user", "user id", messageDto.getUserId()));
//            Message message = Message.builder()
//                    .sendUser(sendUser)
//                    .message(messageDto.getMessage())
//                    .build();
//            messageRepository.save(message);
//            return new ApiResponse<>("Xabar yuborildi", true);
//        } catch (Exception e) {
//            return new ApiResponse<>("Xabar xato", false);
//        }
//    }

//    public List<MessageDto> getMessages(UUID id) {
//        User user = authRepository.findById(id).orElseThrow(() -> new Project.Restaurantbackend.exception.ResourceNotFoundException(404, "get user", "user id", id));
//        List<MessageDto> messageDtoList = new ArrayList<>();
//        for (Message message : messageRepository.findAll()) {
//            if (message.getSendUser().getId().equals(id)) {
//                MessageDto messageDto = MessageDto.builder()
//                        .id(message.getId())
//                        .message(message.getMessage())
//                        .messageReply(message.getReplyMessage())
//                        .build();
//                messageDtoList.add(messageDto);
//            }
//        }
//        return messageDtoList;
//    }

//    public List<MessageDto> getAdminMessages() {
//        List<MessageDto> messageDtoList = new ArrayList<>();
//        List<Message> messages = messageRepository.findAll();
////        for (Message message : messageRepository.findAll()) {
////            MessageDto messageDto = MessageDto.builder()
////                    .id(message.getId())
////                    .user(message.getSendUser())
////                    .message(message.getMessage())
////                    .messageReply(message.getReplyMessage())
////                    .build();
////            messageDtoList.add(messageDto);
////        }
//        for (int i = messages.size() - 1; i >= 0; i--) {
//            MessageDto messageDto = MessageDto.builder()
//                    .id(messages.get(i).getId())
//                    .user(messages.get(i).getSendUser())
//                    .message(messages.get(i).getMessage())
//                    .messageReply(messages.get(i).getReplyMessage())
//                    .build();
//            messageDtoList.add(messageDto);
//        }
//        return messageDtoList;
//    }

//    public ApiResponse<?> adminReplyUser(UUID messageId, MessageDto messageDto) {
//        try {
//            Message message = messageRepository.findById(messageId).orElseThrow(() -> new Project.Restaurantbackend.exception.ResourceNotFoundException(404, "get message", "message id", messageId));
//            if (message.getReplyMessage() == null) {
//                message.setReplyMessage(messageDto.getMessageReply());
//                messageRepository.save(message);
//                return new ApiResponse<>("Javob qaytarildi", true);
//            } else {
//                message.setReplyMessage(messageDto.getMessageReply());
//                messageRepository.save(message);
//                return new ApiResponse<>("Qaytarilgan javob o'chirildi", true);
//            }
//        } catch (Exception e) {
//            return new ApiResponse<>("Javob qaytarishda xatolik", false);
//        }
//    }

//    public ApiResponse<?> adminReplyDelete(UUID id) {
//        try {
//            Message message = messageRepository.findById(id).orElseThrow(() -> new Project.Restaurantbackend.exception.ResourceNotFoundException(404, "egt message", "message id", id));
//            message.setReplyMessage(null);
//            messageRepository.save(message);
//            return new ApiResponse<>("Javob o'chirildi", true);
//        } catch (Exception e) {
//            return new ApiResponse<>("Javob o'chirildida xatolik", true);
//        }
//    }
}